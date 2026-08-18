package com.bookstore.service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.BookSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findNewArrivals(int limit) {
        return bookRepository.findAll(
                PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt"))).getContent();
    }

    public Page<Book> search(String keyword, Long categoryId, BigDecimal minPrice, BigDecimal maxPrice,
                             String sort, int page, int size) {
        Sort sortBy = resolveSort(sort);
        Pageable pageable = PageRequest.of(page, size, sortBy);
        return bookRepository.findAll(
                BookSpecifications.filter(keyword, categoryId, minPrice, maxPrice), pageable);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    private Sort resolveSort(String sort) {
        if ("priceAsc".equals(sort)) {
            return Sort.by(Sort.Direction.ASC, "price");
        }
        if ("priceDesc".equals(sort)) {
            return Sort.by(Sort.Direction.DESC, "price");
        }
        if ("yearDesc".equals(sort)) {
            return Sort.by(Sort.Direction.DESC, "publishedYear");
        }
        if ("titleAsc".equals(sort)) {
            return Sort.by(Sort.Direction.ASC, "title");
        }
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }

}