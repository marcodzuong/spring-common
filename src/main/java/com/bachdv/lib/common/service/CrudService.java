package com.bachdv.lib.common.service;

/**
 * @author bachdv
 */
public interface CrudService<T,ID> extends
        InsertService<T, ID>,
        UpdateService<T,ID>,
        DeleteService<T,ID>,
        SelectService<T, ID> {
}

