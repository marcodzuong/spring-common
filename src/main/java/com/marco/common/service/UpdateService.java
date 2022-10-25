package com.marco.common.service;

/**
 * @author MarcoDuong
 */
public interface UpdateService <T, ID> {
    T update(T record);
    void update(Iterable<T> list);
}

