package ru.backend.UserService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.model.Book;
import ru.backend.UserService.services.book.BookService;
import ru.backend.UserService.services.user.UserService;

import java.util.List;

@Controller
@RequestMapping("people")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users/users_table";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") int id, Model model) {
        AppUser appUser = userService.getUserById(id);
        List<Book> userBooks = bookService.getBooksByUserId(appUser.getId());
        model.addAttribute("appUser", appUser);
        model.addAttribute("books", userBooks);
        return "users/user_page";
    }

    @GetMapping("/new")
    public String addUserForm(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "users/add_user_form";
    }

    @PostMapping
    public String addUser(@ModelAttribute("appUser") AppUser appUser) {
        userService.add(appUser);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") int id){
        model.addAttribute("appUser", userService.getUserById(id));
        return "users/edit_user";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("appUser") AppUser appUser){
        userService.edit(appUser);
        return "redirect:/people";
    }
//
//    @DeleteMapping("/{id}")
//    public String deleteUser(@PathVariable("id") int id){
//        userService.delete(id);
//        return "redirect:/users";
//    }

    @GetMapping("/fillUsersDb")
    public String fillUsersTable() {
        userService.add(new AppUser("Александр", "Зотов", "Андреевич", 1978));
        userService.add(new AppUser("Виталий", "Мирин", "Александрович", 1988));
        userService.add(new AppUser("Андрей", "Джугашвили", "Петрович", 1999));
        userService.add(new AppUser("Сергей", "Ериков", "Сидорович", 2003));
        userService.add(new AppUser("Евпатий", "Тахто", "Васильевич", 2001));
        return "users/users_table";
    }

}
