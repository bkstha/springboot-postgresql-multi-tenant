package com.journal.app.models.DTO;

import java.util.List;

public class PageResponseDTO<T> {
    public static final int DEFAULT_OFFSET = 0;
    public static final int DEFAULT_MAX_NO_OF_ROWS = 100;

    private int offset = DEFAULT_OFFSET;
    private int limit = DEFAULT_MAX_NO_OF_ROWS;
    private long totalElements;
    private List<T> elements;

    public PageResponseDTO(List<T> elements, long totalElements, int offset, int limit) {
        this.elements = elements;
        this.totalElements = totalElements;
        this.offset = offset;
        this.limit = limit;
    }

    public boolean hasMore() {
        return totalElements > offset + limit;
    }

    public boolean hasPrevious() {
        return offset > 0 && totalElements > 0;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public List<T> getElements() {
        return elements;
    }
}
