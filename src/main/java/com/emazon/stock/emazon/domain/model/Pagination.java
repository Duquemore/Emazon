package com.emazon.stock.emazon.domain.model;

import java.util.List;

public class Pagination<T> {
    private final List<T> elements;
    private final long totalElements;
    private final int totalPages;

    public Pagination(List<T> elements, long totalElements, int totalPages) {
        this.elements = elements;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<T> getElements() {
        return elements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
