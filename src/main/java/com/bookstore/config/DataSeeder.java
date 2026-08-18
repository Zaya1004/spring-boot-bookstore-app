package com.bookstore.config;

import com.bookstore.model.Book;
import com.bookstore.model.Category;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataSeeder {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

    @Bean
    CommandLineRunner seed(CategoryRepository categoryRepository, BookRepository bookRepository) {
        return args -> {
            if (bookRepository.count() > 0) {
                return;
            }

            List<Category> categories = categoryRepository.saveAll(List.of(
                    new Category("Fiction"),
                    new Category("Science & Tech"),
                    new Category("History"),
                    new Category("Business"),
                    new Category("Fantasy"),
                    new Category("Biography")));
            log.info("Seeded {} categories", categories.size());

            List<Book> books = new ArrayList<>();
            books.add(book("The Silent Patient", "Alex Michaelides", "9781250301697",
                    new BigDecimal("14.99"), 2019,
                    "A gripping psychological thriller about a woman who stops speaking after committing an unthinkable act.",
                    categories.get(0)));
            books.add(book("The Midnight Library", "Matt Haig", "9780525559474",
                    new BigDecimal("16.99"), 2020,
                    "Between life and death there is a library, and within that library, the shelves go on forever.",
                    categories.get(0)));
            books.add(book("Atomic Habits", "James Clear", "9780735211292",
                    new BigDecimal("18.99"), 2018,
                    "An easy and proven way to build good habits and break bad ones.",
                    categories.get(3)));
            books.add(book("Clean Code", "Robert C. Martin", "9780132350884",
                    new BigDecimal("39.99"), 2008,
                    "A handbook of agile software craftsmanship.",
                    categories.get(1)));
            books.add(book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "9780062316097",
                    new BigDecimal("22.99"), 2015,
                    "How our species came to rule the world.",
                    categories.get(2)));
            books.add(book("The Pragmatic Programmer", "David Thomas, Andrew Hunt", "9780135957059",
                    new BigDecimal("34.99"), 2019,
                    "Your journey to mastery in software engineering.",
                    categories.get(1)));
            books.add(book("The Hobbit", "J.R.R. Tolkien", "9780547928227",
                    new BigDecimal("13.99"), 1937,
                    "A comfortable, friendless hobbit finds himself caught up in a grand quest.",
                    categories.get(4)));
            books.add(book("Steve Jobs", "Walter Isaacson", "9781451648539",
                    new BigDecimal("19.99"), 2011,
                    "The exclusive biography of Steve Jobs based on more than forty interviews.",
                    categories.get(5)));
            books.add(book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565",
                    new BigDecimal("10.99"), 1925,
                    "The story of the mysteriously wealthy Jay Gatsby and his love for the beautiful Daisy Buchanan.",
                    categories.get(0)));
            books.add(book("Thinking, Fast and Slow", "Daniel Kahneman", "9780374533557",
                    new BigDecimal("17.99"), 2011,
                    "A masterpiece revealing the two systems that drive the way we think.",
                    categories.get(3)));
            books.add(book("A Brief History of Time", "Stephen Hawking", "9780553380163",
                    new BigDecimal("15.99"), 1988,
                    "From the Big Bang to black holes, a landmark volume in science writing.",
                    categories.get(1)));
            books.add(book("Educated", "Tara Westover", "9780399590504",
                    new BigDecimal("15.99"), 2018,
                    "A memoir about a young girl who kept out of school, leaves her survivalist family to pursue learning.",
                    categories.get(5)));

            bookRepository.saveAll(books);
            log.info("Seeded {} books", books.size());
        };
    }

    private Book book(String title, String author, String isbn, BigDecimal price, int year,
                      String description, Category category) {
        String cover = "https://placehold.co/300x450/1f2937/ffffff?text="
                + title.replace(" ", "+").substring(0, Math.min(20, title.length()));
        return new Book(title, author, isbn, price, year, description, cover, category);
    }

}