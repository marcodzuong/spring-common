package com.marco.common.service;



import com.marco.common.query.DynamicSpecifications;
import com.marco.common.query.Page;
import com.marco.common.query.SearchFilter;
import com.marco.common.repository.BaseRepository;
import com.marco.common.util.Lists;
import com.marco.common.util.ObjectMapperUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author MarcoDuong
 */
public abstract class BaseService<DOMAIN, ID, R extends BaseRepository<DOMAIN, ID>> implements CrudService<DOMAIN, ID> {

    protected abstract R getDao();

    @Override
    public DOMAIN insert(DOMAIN record) {
        return getDao().save(record);
    }

    @Override
    public void delete(DOMAIN record) {
        getDao().delete(record);
    }

    @Override
    public void deleteById(ID id) {
        getDao().deleteById(id);
    }

    @Override
    public void delete(Iterable<ID> ids) {
        for (ID id : ids) {
            getDao().deleteById(id);
        }
    }

    @Override
    public void deleteAll(Iterable<DOMAIN> list) {
        getDao().deleteInBatch(list);
    }

    @Override
    public void clear() {

    }

    @Override
    public DOMAIN update(DOMAIN record) {
        return null;
    }

    @Override
    public void update(Iterable<DOMAIN> list) {

    }

    @Override
    public DOMAIN get(ID id) {
        Optional<DOMAIN> o = getDao().findById(id);
        return o.orElse(null);
    }

    @Override
    public DOMAIN get(SearchFilter filter) {
        List<DOMAIN> list = queryAll(filter);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public DOMAIN get(List<SearchFilter> filters) {
        List<DOMAIN> list = queryAll(filters);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Object get(String sql, Class<?> klass) {
        return getDao().getBySql(sql, klass);
    }

    @Override
    public List<Map> queryBySql(String sql) {
        return getDao().queryBySql(sql);
    }

    @Override
    public Map getMapBySql(String sql) {
        List<Map> list = queryBySql(sql);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<DOMAIN> query(Iterable<ID> ids) {
        return getDao().findAllById(ids);
    }

    @Override
    public List<DOMAIN> queryAll() {
        return getDao().findAll();
    }

    @Override
    public Page<DOMAIN> queryPage(Page<DOMAIN> page) {
        Pageable pageable = null;
        if (page.getSort() != null) {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), page.getSort());
        } else {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), Sort.Direction.DESC, "id");
        }
        Specification<DOMAIN> specification = DynamicSpecifications.bySearchFilter(page.getFilters(), getDao().getDataClass());
        org.springframework.data.domain.Page<DOMAIN> pageResult = getDao().findAll(specification, pageable);
        page.setTotal(Integer.parseInt(pageResult.getTotalElements() + ""));
        page.setRecords(pageResult.getContent());
        return page;
    }
    public <DTO> Page<DTO> queryPageDto(Page<DTO> page, Class<DTO> outCLass) {
        Pageable pageable ;
        if (page.getSort() != null) {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), page.getSort());
        } else {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), Sort.Direction.DESC, "id");
        }
        Specification<DOMAIN> specification = DynamicSpecifications.bySearchFilter(page.getFilters(), getDao().getDataClass());
        org.springframework.data.domain.Page<DOMAIN> pageResult = getDao().findAll(specification, pageable);
        page.setTotal(Integer.parseInt(pageResult.getTotalElements() + ""));
        page.setRecords(ObjectMapperUtils.mapAll(pageResult.getContent(), outCLass));
        return page;
    }

    @Override
    public List<DOMAIN> queryAll(List<SearchFilter> filters) {
        return queryAll(filters, null);
    }

    @Override
    public List<DOMAIN> queryAll(Sort sort) {
        return getDao().findAll(sort);
    }

    @Override
    public List<DOMAIN> queryAll(List<SearchFilter> filters, Sort sort) {
        Specification<DOMAIN> specification = DynamicSpecifications.bySearchFilter(filters, getDao().getDataClass());
        if (sort == null) {
            return getDao().findAll(specification);
        }
        return getDao().findAll(specification, sort);

    }

    @Override
    public List<DOMAIN> queryAll(SearchFilter filter) {
        return queryAll(filter, null);
    }

    @Override
    public List<DOMAIN> queryAll(SearchFilter filter, Sort sort) {
        if (filter != null) {
            return queryAll(Lists.newArrayList(filter), sort);
        } else {
            return queryAll(new ArrayList<>(), sort);
        }
    }

    @Override
    public long count(SearchFilter filter) {
        return count(Lists.newArrayList(filter));
    }

    @Override
    public long count() {
        return getDao().count();
    }

    @Override
    public long count(List<SearchFilter> filters) {
        Specification<DOMAIN> specification = DynamicSpecifications.bySearchFilter(filters, getDao().getDataClass());
        return getDao().count(specification);
    }

    public Page<DOMAIN> queryPage(Page<DOMAIN> page, String... idNames) {
        Pageable pageable = null;
        if (page.getSort() != null) {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), page.getSort());
        } else {
            pageable = PageRequest.of(page.getCurrent() - 1, page.getSize(), Sort.Direction.DESC, idNames);
        }
        Specification<DOMAIN> specification = DynamicSpecifications.bySearchFilter(page.getFilters(), getDao().getDataClass());
        org.springframework.data.domain.Page<DOMAIN> pageResult = getDao().findAll(specification, pageable);
        page.setTotal(Integer.parseInt(pageResult.getTotalElements() + ""));
        page.setRecords(pageResult.getContent());
        return page;
    }

}
