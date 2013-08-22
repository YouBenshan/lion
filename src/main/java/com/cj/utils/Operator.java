package com.cj.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public enum Operator {
		EQ{
			@Override
			public Predicate predicate(Path path,String value,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(path, value);
			}
		},
		LIKE{
			@Override
			public Predicate predicate(Path path,String value,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.like(path, "%" + value + "%");
			}
		},  
		GT{
			@Override
			public Predicate predicate(Path path,String value,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.greaterThan(path, value);
			}
		},
		LT{
			@Override
			public Predicate predicate(Path path,String value,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.lessThan(path, value);
			}
		},
		GTE  {
			@Override
			public Predicate predicate(Path path,String value,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.greaterThanOrEqualTo(path,value);
			}
		},
		LTE {
			@Override
			public Predicate predicate(Path path,String value,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.lessThanOrEqualTo(path,value);
			}
		};
		public abstract Predicate predicate(Path path,String value, CriteriaBuilder criteriaBuilder);
	}