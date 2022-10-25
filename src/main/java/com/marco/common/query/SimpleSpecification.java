package com.marco.common.query;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.Collection;
import java.util.Date;

/**
 * @author MarcoDuong
 */
public class SimpleSpecification <T> implements Specification<T> {

    private final Collection<SearchFilter> filters;

    public SimpleSpecification(Collection<SearchFilter> operators) {
        this.filters = operators;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (filters !=null && !filters.isEmpty()) {
            int index = 0;

            Predicate resultPre = null;
            for (SearchFilter op : filters) {
                if (index++ == 0) {
                    resultPre = generatePredicate(root, cb, op);
                    continue;
                }
                Predicate pre = generatePredicate(root, cb, op);
                if (pre == null) {
                    continue;
                }
                switch (op.join) {
                    case and:
                        resultPre = cb.and(resultPre, pre);
                        break;
                    case or:
                        resultPre = cb.or(resultPre, pre);
                        break;
                    default:
                        break;
                }
            }
            return resultPre;
        }
        return  cb.conjunction();
    }

    private Predicate generatePredicate(Root<T> root, CriteriaBuilder cb, SearchFilter op) {
        Object value = op.value;
        String[] names = StringUtils.split(op.fieldName, ".");
        if (names==null) return null;
        Path expression = root.get(names[0]);
        for (int i = 1; i < names.length; i++) {
            expression = expression.get(names[i]);
        }


        switch (op.operator) {
            case EQ:
                return cb.equal(expression, value);
            case NE:
                return cb.notEqual(expression, value);
            case GTE:
                return cb.greaterThanOrEqualTo(expression, (Comparable) value);
            case LTE:
                return cb.lessThanOrEqualTo(expression , (Comparable) value);
            case GT:
                return cb.greaterThan(expression, (Comparable) value);
            case LT:
                return cb.lessThan(expression, (Comparable) value);
            case IN:
                if(value.getClass().isArray()){
                    return expression.in((Object[]) value);
                } else{
                    return expression.in((Collection) value);
                }
            case NOTIN:
                if (value instanceof Collection) {
                    return cb.not(expression.in((Collection) value));
                }
                return cb.not(expression.in(value));
            case LIKE:
                return cb.like(expression.as(String.class), "%" + value + "%");
            case LIKEL:
                return cb.like(expression.as(String.class), value + "%");
            case LIKER:
                return cb.like(expression.as(String.class), "%" + value);
            case ISNULL:
                return cb.isNull(expression);
            case ISNOTNULL:
                return cb.isNotNull(expression);
            case BETWEEN:
                if (value instanceof Date[]) {
                    Date[] dateArray = (Date[]) value;
                    return cb.between(expression, dateArray[0], dateArray[1]);
                }
            default:
                return null;
        }
    }
}

