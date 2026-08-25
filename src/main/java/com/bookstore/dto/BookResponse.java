package com.bookstore.dto;

import java.math.BigDecimal;

public record BookResponse(Long id, String title, String isbn, BigDecimal price, Integer stockQuantity, boolean active,
		Long categoryId, Long authorId, String categoryName, String authorFirstName, String authorLastname) {

}
