package ru.backend.UserService.services.book;



import ru.backend.UserService.model.Book;

import java.util.List;

public interface BookService {
    void add(Book book);
    void edit(Book book);
    Book getBookById(int id);
    List<Book> getBooksList(Integer pageNumber, Integer pageSize, Boolean sortByYearOfBook);
    List<Book> getBooksByUserId(int userId);
    List<Book> getBooksStartingWith(String query);

}
