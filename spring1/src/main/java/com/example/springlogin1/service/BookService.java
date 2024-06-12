package com.example.springlogin1.service;

import com.example.springlogin1.dao.IBookDAO;
import com.example.springlogin1.model.Book;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {
    @Autowired
    private final IBookDAO bookDAO;

    public BookService(IBookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Book> getAll() {
        return this.bookDAO.getAll();
    }

    @Transactional
    public void saveOrUpdate(Book book) {
        this.bookDAO.saveOrUpdate(book);
    }

    @Override
    @Transactional
    public void delete(int id) {
        this.bookDAO.delete(id);
    }

    @Transactional
    public void deleteBooksWithZeroQuantity() {
        List<Book> books = bookDAO.getAll();
        for (Book book : books) {
            if (book.getQuantity() == 0) {
                delete(book.getId());
            }
        }
    }
}