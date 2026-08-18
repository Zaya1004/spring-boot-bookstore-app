package com.bookstore.controller;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.web.SearchForm;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/books")
public class BookController {

    private static final int PAGE_SIZE = 12;

    private final BookService bookService;
    private final CategoryService categoryService;

    public BookController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String list(@ModelAttribute SearchForm searchForm,
                       @RequestParam(defaultValue = "0") int page,
                       Model model) {
        Page<Book> books = bookService.search(
                searchForm.getKeyword(),
                searchForm.getCategoryId(),
                searchForm.getMinPrice(),
                searchForm.getMaxPrice(),
                searchForm.getSort(),
                page,
                PAGE_SIZE);

        model.addAttribute("books", books);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("activePage", "books");
        return "books/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("related", bookService.search(
                null, book.getCategory().getId(), null, null, "newest", 0, 4).getContent());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("activePage", "books");
        return "books/detail";
    }

}