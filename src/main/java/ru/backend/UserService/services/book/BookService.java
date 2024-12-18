package ru.backend.UserService.services.book;



import ru.backend.UserService.model.Book;

import java.util.List;

public interface BookService {
    void add(Book book);

    List<Book> listBooks();

}
