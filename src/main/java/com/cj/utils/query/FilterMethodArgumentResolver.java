package com.cj.utils.query;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

@Slf4j
public class FilterMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private static final String FIELD_NAME_SEPERATOR=".";
	
		
  @Override public boolean supportsParameter(MethodParameter parameter) {
    return ClassUtils.isAssignable(parameter.getParameterType(), Filter.class);
//    return false;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) throws Exception {
	  
	  ServletModelAttributeMethodProcessor modelAttributeMethodProcessor=new ServletModelAttributeMethodProcessor(Boolean.FALSE);
	  Filter filter=(Filter)modelAttributeMethodProcessor.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
	  
	  Search search=parameter.getParameterAnnotation(Search.class);
	  Class<?> clazz=search.value();
	  
	  Table<Operation, String, Object> table=this.getTable(filter, clazz, webRequest, binderFactory);
	  
	  Specification specification=getSpecification(table);
	  
	  filter.setSpecification(specification);
	  return filter;
  	}
  
  private Object getValue(Class<?> clazz, String name, String value, NativeWebRequest webRequest,WebDataBinderFactory binderFactory) throws Exception{
	  Object target= BeanUtils.instantiateClass(clazz);
	  String objectName=clazz.getName();
	  WebDataBinder unitaryWebDataBinder=binderFactory.createBinder(webRequest, target, objectName);
	  MutablePropertyValues propertyValues = new MutablePropertyValues();
	  propertyValues.add(name, value);
	  unitaryWebDataBinder.bind(propertyValues);
	  return PropertyUtils.getNestedProperty(target, name);
  }
  
  private Table<Operation, String, Object> getTable(Filter filter, Class<?> clazz, NativeWebRequest webRequest,WebDataBinderFactory binderFactory) throws Exception{
	  Table<Operation, String, Object> table = HashBasedTable.create();
	  EnumMap<Operation, Map<String,String>> unitaryOperations= filter.getUnitaries();
	  for(Entry<Operation,Map<String,String>> operationEntry: unitaryOperations.entrySet()){
		  Operation operation=operationEntry.getKey();
		  for(Entry<String,String> field:operationEntry.getValue().entrySet()){
			  String name=field.getKey();
			  String valueString=field.getValue();
			  if(StringUtils.isNotBlank(valueString)){
				  Object value= this.getValue(clazz, name, valueString, webRequest, binderFactory);
				  table.put(operation, name, value);
			  }
		  }
	  }
	  EnumMap<Operation, Map<String,String[]>> polybasicOoperations= filter.getPolybasics();
	  for(Entry<Operation,Map<String,String[]>> operationEntry:polybasicOoperations.entrySet()){
		  Operation operation=operationEntry.getKey();
		  for(Entry<String,String[]> fileds:operationEntry.getValue().entrySet()){
			  String name=fileds.getKey();
			  List<Object> values=new ArrayList<Object>();
			  
			  for(String valueString:fileds.getValue()){
				  if(StringUtils.isNotBlank(valueString)){
					  Object value= this.getValue(clazz, name, valueString, webRequest, binderFactory);
					  values.add(value);
				  }
				  
			  }
			  if(values.size()>0){
				  table.put(operation, name, values.toArray());
			  }
			  
		  }
	  }
	return table;
  }

	private Path getPath(Root root, String fieldName) {
		String[] names = StringUtils.split(fieldName, FIELD_NAME_SEPERATOR);
		Path path = root.get(names[0]);
		for (int i = 1; i < names.length; i++) {
			path = path.get(names[i]);
		}
		return path;
	}
  
  private Specification getSpecification(final Table<Operation, String, Object> table){
	  Specification specification =new Specification(){
			@Override
			public Predicate toPredicate(Root root, CriteriaQuery query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = Lists.newArrayList();
				for(Cell<Operation, String, Object> cell : table.cellSet() ){
					String fieldName=cell.getColumnKey();
					Path path = getPath(root, fieldName);
					predicates.add(cell.getRowKey().predicate(path, cb,cell.getValue()));
				}
				if (predicates.size() > 0) {
					return cb.and(predicates.toArray(new Predicate[predicates.size()]));
				}else{
					return cb.conjunction();
				}
			}
			  
		  };
		 return specification;
  }
}
