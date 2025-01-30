package ru.backend.UserService.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.UserService.model.Book;
import ru.backend.UserService.repository.user.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public List<Book> getBooksByUserId(int userId) {
        return bookRepository.findAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void edit(Book newBook) {
        bookRepository.save(newBook);
    }
}
