package ru.backend.UserService.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.UserService.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
