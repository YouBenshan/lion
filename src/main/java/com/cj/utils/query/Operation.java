package com.cj.utils.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

public enum Operation {
	EQ {
		@Override
		public Predicate predicate(Path<?> path,
				CriteriaBuilder criteriaBuilder, Object value) {
			return criteriaBuilder.equal(path, value);
		}
	},
	LIKE {
		@Override
		public Predicate predicate(Path<?> path,
				CriteriaBuilder criteriaBuilder, Object value) {
			return criteriaBuilder.like((Path<String>) path, "%" + value + "%");
		}
	},
	GT {
		@Override
		public Predicate predicate(Path<?> path,
				CriteriaBuilder criteriaBuilder, Object value) {
			return criteriaBuilder.greaterThan((Path<Comparable>) path,
					(Comparable) value);
		}
	},
	LT {
		@Override
		public Predicate predicate(Path<?> path,
				CriteriaBuilder criteriaBuilder, Object value) {
			return criteriaBuilder.lessThan((Path<Comparable>) path,
					(Comparable) value);
		}
	},
	GTE {
		@Override
		public Predicate predicate(Path<?> path,
				CriteriaBuilder criteriaBuilder, Object value) {
			return criteriaBuilder.greaterThanOrEqualTo(
					(Path<Comparable>) path, (Comparable) value);
		}
	},
	LTE {
		@Override
		public Predicate predicate(Path<?> path,
				CriteriaBuilder criteriaBuilder, Object value) {
			return criteriaBuilder.lessThanOrEqualTo((Path<Comparable>) path,
					(Comparable) value);
		}
	},

	IN {

		@Override
		public Predicate predicate(Path<?> path,
				CriteriaBuilder criteriaBuilder, Object value) {
			return path.in((Object[]) value);
		}
	};
	public abstract Predicate predicate(Path<?> path,
			CriteriaBuilder criteriaBuilder, Object value);

}
