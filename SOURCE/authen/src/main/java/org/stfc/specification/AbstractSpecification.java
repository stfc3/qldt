package org.stfc.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;


public class AbstractSpecification<T> implements Specification<T> {

	private static final long serialVersionUID = 1L;
	
	private SpecSearchCriteria criteria;

	public AbstractSpecification(final SpecSearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	public SpecSearchCriteria getCriteria() {
		return criteria;
	}
 
	@Override
	public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
		switch (criteria.getOperation()) {
		case EQUALITY:
			if (criteria.getValue() instanceof Date) {
				return builder.equal(root.<Date>get(criteria.getKey()).as(java.sql.Date.class), (Date)criteria.getValue());
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		case NEGATION:
			if (criteria.getValue() instanceof Date) {
				return builder.notEqual(root.<Date>get(criteria.getKey()).as(java.sql.Date.class), (Date)criteria.getValue());
			} else {
				return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
			}
		case GREATER_THAN:
			if (criteria.getValue() instanceof Date) {
				return builder.greaterThan(root.<Date>get(criteria.getKey()).as(java.sql.Date.class), (Date)criteria.getValue());
			} else {
				return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
			}
		case GREATER_THAN_OR_EQUAL:
			if (criteria.getValue() instanceof Date) {
				return builder.greaterThanOrEqualTo(root.<Date>get(criteria.getKey()).as(java.sql.Date.class), (Date)criteria.getValue());
			} else {
				return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
			}
		case LESS_THAN:
			if (criteria.getValue() instanceof Date) {
				return builder.lessThan(root.<Date>get(criteria.getKey()).as(java.sql.Date.class), (Date)criteria.getValue());
			} else {
				return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
			}
		case LESS_THAN_OR_EQUAL:
			if (criteria.getValue() instanceof Date) {
				return builder.lessThanOrEqualTo(root.<Date>get(criteria.getKey()).as(java.sql.Date.class), (Date)criteria.getValue());
			} else {
				return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
			}
		case STARTS_WITH:
			return builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
		case ENDS_WITH:
			return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
		case CONTAINS:
			return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
		default:
			return null;
		}
	}
}
