package com.journal.app.models.DTO;

public class PageRequestDTO {

    private String sort;
    private String orderBy;
    private int offset;
    private int limit;

    public PageRequestDTO(String sort, String orderBy, int offset, int limit) {
        this.sort = sort;
        this.orderBy = orderBy;
        this.offset = offset;
        this.limit = limit;
    }

    public PageRequestDTO() {
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
