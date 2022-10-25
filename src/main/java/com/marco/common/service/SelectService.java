package com.marco.common.service;

import com.marco.common.query.Page;
import com.marco.common.query.SearchFilter;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * @author MarcoDuong
 */
public interface SelectService <T, ID> {
    T get(ID id);
    T get(SearchFilter filter);
    T get(List<SearchFilter> filters);
    Object get(String sql,Class<?> klass);
    List<Map> queryBySql(String sql);
    Map getMapBySql(String sql);

    List<T> query(Iterable<ID> ids);

    List<T> queryAll();

    Page<T> queryPage(Page<T> page);

    List<T> queryAll(List<SearchFilter> filters);

    List<T> queryAll(Sort sort);

    List<T> queryAll(List<SearchFilter> filters, Sort sort);

    List<T> queryAll(SearchFilter filter);

    List<T> queryAll(SearchFilter filter,Sort sort);

    long count(SearchFilter filter);

    long count();

    long count(List<SearchFilter> filters);
}

