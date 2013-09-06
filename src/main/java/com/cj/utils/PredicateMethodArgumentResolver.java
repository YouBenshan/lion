package com.cj.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PredicateMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

	private static final String PREDICATE_PARAMETER_NAME = "filter";
	private static final String PREDICATE_VALUE_SEPERATOR = ",";

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		Specifications specifications = Specifications.where(null);
		HttpServletRequest request = (HttpServletRequest) webRequest
				.getNativeRequest();
		String[] filters = request.getParameterValues(PREDICATE_PARAMETER_NAME);
		for (String filter : filters) {
			String[] strs = filter.split(PREDICATE_VALUE_SEPERATOR);
			if (strs.length != 3) {
				throw new IllegalArgumentException(filter
						+ " is not a valid search filter formate");
			}
			for (String str : strs) {
				if (str.length() == 0) {
					throw new IllegalArgumentException(filter
							+ " is not a valid search filter formate");
				}
			}
			final String fieldName = strs[0];
			final Operator operator = Operator.valueOf(strs[1]);
			final String value = strs[2];

			if (operator == null) {
				throw new IllegalArgumentException(filter
						+ " is not a valid search filter formate");
			}

			Specification specification = new Specification() {
				@Override
				public Predicate toPredicate(Root root, CriteriaQuery query,
						CriteriaBuilder criteriaBuilder) {
					String[] names = StringUtils.split(fieldName, ".");
					Path path = root.get(names[0]);
					for (int i = 1; i < names.length; i++) {
						path = path.get(names[i]);
					}
					Predicate predicate = operator.predicate(path, value,
							criteriaBuilder);
					return predicate;
				}
			};
		}
		return specifications;
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return ClassUtils.isAssignable(parameter.getParameterType(),
				Predicate.class);
	}
}
