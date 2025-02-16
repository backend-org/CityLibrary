package ru.backend.UserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.UserService.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
}
