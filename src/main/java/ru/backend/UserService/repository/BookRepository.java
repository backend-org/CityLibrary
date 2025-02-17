package ru.backend.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.UserService.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByUserId(int id);
    List<Book> findByTitleStartingWithIgnoreCase(String query);
}
