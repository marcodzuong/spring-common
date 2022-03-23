package com.bachdv.lib.common.api;

import io.netty.util.internal.StringUtil;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

/**
 * @author BachDV
 * Date : 22/03/2022
 */
@ToString
@Data
public class CommonPage<T> {

    private Sort sort;
    private transient int offset;
    private transient int limit;
    private int total;
    private int size = 10;
    private int pages;
    private int current = 1;
    private transient boolean searchCount = true;

    private List<T> records = Collections.emptyList();


    public CommonPage() {

    }

    public CommonPage(int current, int size, String orderByField) {
        this(current,size,orderByField,true);
    }

    public CommonPage(int current, int size, String orderByField, boolean isAsc) {
        this(current, size);
        setSort(Sort.by(isAsc? Sort.Direction.ASC: Sort.Direction.DESC,orderByField));

    }

    public CommonPage(int current, int size) {
        this(current,size,true);
    }


    public CommonPage(int current, int size, boolean searchCount) {

        setOffset(offsetCurrent(current, size));
        setLimit(size);
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.searchCount = searchCount;
    }

    protected static int offsetCurrent(int current, int size) {
        if (current > 0) {
            return (current - 1) * size;
        }
        return 0;
    }

    public int offsetCurrent() {
        return offsetCurrent(this.current, this.size);
    }

    public boolean hasPrevious() {
        return this.current > 1;
    }

    public boolean hasNext() {
        return this.current < this.pages;
    }


    public CommonPage setTotal(int total) {
        this.total = total;
        return this;
    }


    public CommonPage setSize(int size) {
        this.size = size;
        return this;
    }

    public int getPages() {
        if (this.size == 0) {
            return 0;
        }
        this.pages = this.total / this.size;
        if (this.total % this.size != 0) {
            this.pages++;
        }
        return this.pages;
    }

    public CommonPage setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
        return this;
    }

    public CommonPage setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public CommonPage setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    public CommonPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

}
