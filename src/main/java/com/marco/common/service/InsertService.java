package com.marco.common.service;

/**
 * @author MarcoDuong
 */
public interface InsertService<T, ID> {
    T insert(T record);
}
