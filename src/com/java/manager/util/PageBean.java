package com.java.manager.util;

import java.util.List;

public class PageBean<T> {
    //页号默认值
    public static final Integer DEFAULT_PAGENO = 1;
    //每页记录数的默认值
    public static final Integer DEAULT_PAGESIZE = 5;
    //当前页码
    private Integer pageNo;
    //每页条数(记录数)
    private Integer pageSize;
    //总页数
    private Integer totalPage;
    //总记录数 (从数据库查询)
    private Integer totalCount;
    //当前页数据集合(从数据库查询)
    private List<T> data;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    //返回总页数
    public Integer getTotalPage() {
        if (this.totalCount % this.pageSize == 0) {
            return this.totalCount / this.pageSize;
        }
        return this.totalCount / this.pageSize + 1;
    }


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
