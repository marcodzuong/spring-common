package com.bachdv.lib.common.service;

/**
 * @author bachdv
 */
public interface UpdateService <T, ID> {
    T update(T record);
    void update(Iterable<T> list);
}

