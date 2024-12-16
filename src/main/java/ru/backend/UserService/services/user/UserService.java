package ru.backend.UserService.services.user;



import ru.backend.UserService.model.AppUser;

import java.util.List;

public interface UserService {
    void add(AppUser appUser);

    List<AppUser> listUsers();

    AppUser getUserById(int id);

    void edit(AppUser newAppUser);

    void delete(int id);

}
