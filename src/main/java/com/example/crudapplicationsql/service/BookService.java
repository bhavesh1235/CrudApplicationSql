package com.example.crudapplicationsql.service;

import com.example.crudapplicationsql.dto.BookDto;
import java.util.List;


public interface BookService {

    // Save operation
    BookDto saveBook(BookDto book);

    // Read operation
    List<BookDto> fetchBookList();

    // Update operation
    BookDto updateBook(BookDto book, String bookId);

    // Delete operation
    String deleteBookById(String bookId);

    // Fetch Books Based on Rating
    List<BookDto> fetchBooksByRating(Integer rating);
}
