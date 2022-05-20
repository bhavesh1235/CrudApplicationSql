package com.example.crudapplicationsql.service;

import com.example.crudapplicationsql.dto.BookDto;
import com.example.crudapplicationsql.entity.Book;
import com.example.crudapplicationsql.exceptions.BookServiceException;
import com.example.crudapplicationsql.repository.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImplementation implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto saveBook(BookDto bookDto) {
        Book book = new Book();
        bookDto.setBookId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(bookDto,book);
        book.setCreationDate(new Date());
        book.setUpdationDate(null);
        book.setIsDeleted(false);

        bookRepository.save(book);

        return bookDto;
    }

    @Override
    public List<BookDto> fetchBookList() {
        List<Book> bookList = bookRepository.findAll();
        if(bookList.isEmpty()) {
            throw new BookServiceException("No Book Present");
        }
        return convertEntityToDtoList(bookList);
    }

    @Override
    public BookDto updateBook(BookDto bookDto, String bookId) {
        Optional<Book> bookDB = bookRepository.findById(bookId);
        BookDto bookDtoReturn = new BookDto();
        if(!bookDB.isPresent() || Boolean.TRUE.equals(bookDB.get().getIsDeleted())) {
            throw new BookServiceException("No Book Found to update"+ bookId);
        }
        if(Objects.nonNull(bookDto.getBookName())) {
            bookDB.get().setBookName(bookDto.getBookName());
        }
        if(Objects.nonNull(bookDto.getCost())) {
            bookDB.get().setCost(bookDto.getCost());
        }
        if(Objects.nonNull(bookDto.getBookRating())) {
            bookDB.get().setBookRating(bookDto.getBookRating());
        }

        bookDB.get().setUpdationDate(new Date());
        bookRepository.save(bookDB.get());

        BeanUtils.copyProperties(bookDB.get(), bookDtoReturn);
        return bookDtoReturn;
    }

    @Override
    public String deleteBookById(String bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (!book.isPresent()) {
            throw new BookServiceException("No Book Found to delete"+ bookId);
        }
        book.get().setIsDeleted(true);
        bookRepository.save(book.get());
        return "Book "+bookId+" deleted successfully";
    }

    @Override
    public List<BookDto> fetchBooksByRating(Integer rating) {
        List<Book> bookList= bookRepository.findByBookRating(rating);
        if(bookList.isEmpty()){
            throw new BookServiceException("No Book Found");
        }
        return convertEntityToDtoList(bookList);
    }

    public List<BookDto> convertEntityToDtoList(List<Book> bookList){
        List<BookDto> bookDtoList = new ArrayList<>();

        if(bookList.isEmpty()){
            throw new BookServiceException("No Book Found");
        }

        for( Book book : bookList) {
            if(Boolean.FALSE.equals(book.getIsDeleted())) {
                BookDto bookDto = new BookDto();
                BeanUtils.copyProperties(book, bookDto);
                bookDtoList.add(bookDto);
            }
        }

        return bookDtoList;
    }
}
