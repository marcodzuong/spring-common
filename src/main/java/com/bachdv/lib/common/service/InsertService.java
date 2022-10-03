package com.bachdv.lib.common.service;

/**
 * @author bachdv
 */
public interface InsertService<T, ID> {
    T insert(T record);
}
