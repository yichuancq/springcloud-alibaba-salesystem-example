package com.example.common.request;

import java.io.Serializable;

/**
 * 自定义分页请求Dto
 *
 * @author yichuan
 */
public class PageRequest implements Serializable {
    /***
     *页索引
     */
    private Integer pageNumber = 0;
    /***
     *每页大小
     */
    private Integer pageSize = 20;

    public PageRequest() {
    }

    @Override
    public String toString() {
        return "PageRequest{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                '}';
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
