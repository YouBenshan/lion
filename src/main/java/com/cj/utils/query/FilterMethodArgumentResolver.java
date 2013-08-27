package com.cj.utils.query;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;


public class FilterMethodArgumentResolver<T> implements
		HandlerMethodArgumentResolver {

	private static final String FIELD_NAME_SEPERATOR = ".";

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return ClassUtils.isAssignable(parameter.getParameterType(),
				Specification.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		Filter filter = bindFilterFromRequest(webRequest, binderFactory);

		Search search = parameter.getParameterAnnotation(Search.class);
		Class<?> clazz = search.value();

		Table<Operation, String, Object> table = this.getTable(filter, clazz,
				webRequest, binderFactory);

		Specification<?> specification = getSpecification(table);

		return specification;
	}
	
	/*
	 * bind Filter object from request follow the way of
	 * @see org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor
	*/ 
	private Filter bindFilterFromRequest(NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception, BindException {

		Filter filter = BeanUtils.instantiateClass(Filter.class);
		WebDataBinder binder = binderFactory.createBinder(webRequest, filter,
				Filter.class.getName());
		if (binder.getTarget() != null) {
			ServletRequest servletRequest = webRequest
					.getNativeRequest(ServletRequest.class);
			ServletRequestDataBinder servletBinder = (ServletRequestDataBinder) binder;
			servletBinder.bind(servletRequest);
			if (binder.getBindingResult().hasErrors()) {
				throw new BindException(binder.getBindingResult());
			}
		}

		filter = (Filter) binder.getTarget();
		return filter;
	}

	private Object getValue(Class<?> clazz, String name, String value,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
			throws Exception {
		
		if(StringUtils.isBlank(value)){
			return null;
		}
		
		Object target = BeanUtils.instantiateClass(clazz);
		WebDataBinder webDataBinder = binderFactory.createBinder(webRequest,
				target, clazz.getName());
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		propertyValues.add(name, value);
		webDataBinder.bind(propertyValues);
		return PropertyUtils.getNestedProperty(target, name);
	}
	
	private List<Object> getValue(Class<?> clazz, String name, String[] values,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
			throws Exception {
		List<Object> objects=Lists.newArrayList();
		for(String value:values){
			Object object=this.getValue(clazz, name, value, webRequest, binderFactory);
			if(object!=null){
				objects.add(object);
			}
		}
		return objects;
		
	}

	private Table<Operation, String, Object> getTable(Filter filter,
			Class<?> clazz, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		Table<Operation, String, Object> table = HashBasedTable.create();
		
		EnumMap<Operation, Map<String, String>> unitaryOperations = filter
				.getUnitaries();
		for (Entry<Operation, Map<String, String>> operationEntry : unitaryOperations
				.entrySet()) {
			Operation operation = operationEntry.getKey();
			for (Entry<String, String> field : operationEntry.getValue()
					.entrySet()) {
				String name = field.getKey();
				Object value = this.getValue(clazz, name, field.getValue(),
						webRequest, binderFactory);
				if(value!=null){
					table.put(operation, name, value);
				}
				

			}
		}
		
		EnumMap<Operation, Map<String, String[]>> polybasicOoperations = filter
				.getPolybasics();
		for (Entry<Operation, Map<String, String[]>> operationEntry : polybasicOoperations
				.entrySet()) {
			Operation operation = operationEntry.getKey();
			for (Entry<String, String[]> fileds : operationEntry.getValue()
					.entrySet()) {
				String name = fileds.getKey();
				List<Object> values = this.getValue(clazz, name,  fileds.getValue(), webRequest, binderFactory);
				if (values.size() > 0) {
					table.put(operation, name, values.toArray());
				}

			}
		}
		return table;
	}

	private Path<T> getPath(Root<T> root, String fieldName) {
		String[] names = StringUtils.split(fieldName, FIELD_NAME_SEPERATOR);
		Path<T> path = root.get(names[0]);
		for (int i = 1; i < names.length; i++) {
			path = path.get(names[i]);
		}
		return path;
	}

	private Specification<T> getSpecification(
			final Table<Operation, String, Object> table) {
		Specification<T> specification = new Specification<T>() {

			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = Lists.newArrayList();
				for (Cell<Operation, String, Object> cell : table.cellSet()) {
					String fieldName = cell.getColumnKey();
					Path<T> path = getPath(root, fieldName);
					predicates.add(cell.getRowKey().predicate(path, cb,
							cell.getValue()));
				}
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[predicates
							.size()]));
				} else {
					return cb.conjunction();
				}
			}

		};
		return specification;
	}
}
