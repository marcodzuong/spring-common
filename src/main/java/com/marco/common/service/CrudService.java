package com.marco.common.service;

/**
 * @author MarcoDuong
 */
public interface CrudService<T,ID> extends
        InsertService<T, ID>,
        UpdateService<T,ID>,
        DeleteService<T,ID>,
        SelectService<T, ID> {
}

