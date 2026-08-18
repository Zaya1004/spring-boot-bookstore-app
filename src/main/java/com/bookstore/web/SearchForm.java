package com.bookstore.web;

import java.math.BigDecimal;

public class SearchForm {

    private String keyword = "";

    private Long categoryId;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private String sort = "newest";

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean hasFilter() {
        return (keyword != null && !keyword.isBlank())
                || categoryId != null
                || minPrice != null
                || maxPrice != null;
    }

}