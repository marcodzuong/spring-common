package com.bachdv.lib.common.query;

import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

/**
 * @author bachdv
 */
public class DynamicSpecifications {
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
        return new SimpleSpecification<T>(filters);
    }
}
