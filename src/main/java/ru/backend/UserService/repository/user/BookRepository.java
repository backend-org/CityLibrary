package ru.backend.UserService.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.UserService.model.Book;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByUserId(int id);
}
