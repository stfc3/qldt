package org.stfc.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationsBuilder<T> {
	  
	private final List<SpecSearchCriteria> params;

    public SpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public final SpecificationsBuilder<T> with(SearchOperation searchOperation,final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(searchOperation, key, operation, value, prefix, suffix);
    }

    public final SpecificationsBuilder<T> with(final String orPredicate, final String key, final String operation, Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(String.valueOf(operation.charAt(0)));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                    value = "%" + value + "%";
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                    value = "%" + value;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                    value = value + "%";
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }
    
	public Specification<T> build() {

        if (params.size() == 0)
            return null;

        Specification<T> result = new AbstractSpecification<T>(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                .isOrPredicate()
                    ? Specification.where(result)
                        .or(new AbstractSpecification<T>(params.get(i)))
                    : Specification.where(result)
                        .and(new AbstractSpecification<T>(params.get(i)));

        }

        return result;
    }

    public final SpecificationsBuilder<T> with(AbstractSpecification<T> spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final SpecificationsBuilder<T> with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }

}
