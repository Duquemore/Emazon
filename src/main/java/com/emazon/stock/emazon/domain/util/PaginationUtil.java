package com.emazon.stock.emazon.domain.util;

public class PaginationUtil {
    private final int page;
    private final int size;
    private final String sortDirection;

    public PaginationUtil(int page, int size, String sortDirection) {
        this.page = page;
        this.size = size;
        this.sortDirection = sortDirection;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortDirection() {
        return sortDirection;
    }
}
