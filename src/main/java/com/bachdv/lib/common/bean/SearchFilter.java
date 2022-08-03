package com.bachdv.lib.common.bean;

import com.bachdv.lib.common.util.Maps;
import com.bachdv.lib.common.util.StringUtil;

import java.util.Map;

public class SearchFilter {
    public enum Operator {
        EQ, NE, LIKE, LIKEL, LIKER, GT, LT, GTE, LTE, IN, NOTIN, ISNULL, ISNOTNULL, BETWEEN
    }

    public enum Join {
        and, or
    }

    public Join join = Join.and;
    public String fieldName;
    public Object value;
    public Operator operator;

    public static SearchFilter build(String fieldName, Object value) {
        return new SearchFilter(fieldName, Operator.EQ, value);
    }

    public static SearchFilter build(String fieldName,Operator operator) {
        return new SearchFilter(fieldName,operator);
    }
    public static SearchFilter build(String fieldName, Operator operator, Object value) {
        if (StringUtil.isNullOrEmpty(value) || StringUtil.isNullOrEmpty(fieldName)) return null;
        return new SearchFilter(fieldName, operator, value);
    }


    public static SearchFilter build(String fieldName, Object value, Join join) {
        return new SearchFilter(fieldName, Operator.EQ, value, join);

    }
    public SearchFilter(String fieldName, Operator operator) {
        this.fieldName = fieldName;
        this.operator = operator;

    }
    public SearchFilter(String fieldName, Operator operator, Object value) {
        if (!StringUtil.isNullOrEmpty(value)) {
            this.fieldName = fieldName;
            this.value = value;
            this.operator = operator;
        }
    }

    public SearchFilter(String fieldName, Operator operator, Object value, Join join) {
        if (!StringUtil.isNullOrEmpty(value)) {
            this.fieldName = fieldName;
            this.value = value;
            this.operator = operator;
            this.join = join;
        }
    }


    public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = Maps.newHashMap();

        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {

            String key = entry.getKey();
            Object value = entry.getValue();
			/*if (StringUtil.isBlank((String) value)) {
				continue;
			}*/

            String[] names = StringUtil.split(key, "_");
            if (names.length != 2) {
                throw new IllegalArgumentException(key + " is not a valid search filter name");
            }
            String filedName = names[1];
            Operator operator = Operator.valueOf(names[0]);

            SearchFilter filter = new SearchFilter(filedName, operator, value);
            filters.put(key, filter);
        }

        return filters;
    }

}
