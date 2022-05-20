package com.example.crudapplicationsql.controller;

import com.example.crudapplicationsql.dto.BookDto;
import com.example.crudapplicationsql.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // Save operation
    @PostMapping("/book")
    public BookDto saveBook(@RequestBody BookDto bookDto) {
        return bookService.saveBook(bookDto);
    }

    // Fetch operation
    @GetMapping("/book/getAll")
    public List<BookDto> fetchBooks() {
        return bookService.fetchBookList();
    }

    // Update operation
    @PutMapping("/book/{id}")
    public BookDto updateBook(@RequestBody BookDto bookDto, @PathVariable("id") String bookId) {
        return bookService.updateBook(bookDto, bookId);
    }

    // Delete operation
    @DeleteMapping("/book/{id}")
    public String deleteBookById(@PathVariable("id") String bookId) {
        return bookService.deleteBookById(bookId);
    }

    // Fetch Books Based on Rating
    @GetMapping("/book/rating/{rating}")
    public List<BookDto> fetchBooksByRating(@PathVariable("rating") Integer rating) {
        return bookService.fetchBooksByRating(rating);
    }
}
