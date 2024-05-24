package com.example.springlogin1.service;

import java.util.List;
import java.util.Optional;
import com.example.springlogin1.model.Book;



public interface IBookService {
    void saveOrUpdate(Book book);
    Optional<Book> getById(int id);
    List<Book> getAll();
    void delete(int id);
}