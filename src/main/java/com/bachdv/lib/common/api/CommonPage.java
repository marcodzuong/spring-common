package com.bachdv.lib.common.api;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpRequest;

import java.util.List;

/**
 * Paging data encapsulation class
 * @author BachDV
 */
@ToString
@Data
public class CommonPage<T> {

    /**
     * current page number
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * Quantity per page
     */
    private Integer totalPage;
    /**
     * total number
     */
    private Long total;
    /**
     * paginated data
     */
    private List<T> list;

    /**
     * Convert the list after PageHelper paging into paging information
     */
    public static <T> CommonPage<T> restPage(HttpRequest request, List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        result.setPageSize(list.size());
        result.setTotal((long) list.size());
        result.setList(list);
        return result;
    }

    /**
     * Convert SpringData paginated list to paging information
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(pageInfo.getTotalPages());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotalElements());
        result.setList(pageInfo.getContent());
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

}
