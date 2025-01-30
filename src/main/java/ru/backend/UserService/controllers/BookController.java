package ru.backend.UserService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.backend.UserService.model.Book;
import ru.backend.UserService.services.book.BookService;
import ru.backend.UserService.services.user.UserService;

import java.util.Optional;

@Controller
@RequestMapping("books")
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @GetMapping()
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.listBooks());
        return "books/books_table";
    }

//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") int id, Model model) {
//        AppUser appUser = userService.getUserById(id);
//        model.addAttribute("user", appUser);
//        return "users/user_page";
//    }
//
    @GetMapping("/new")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add_book_form";
    }
//
    @PostMapping
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBookForm(Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookService.getBookById(id));
        return "books/edit_book";
    }

    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") Book book) {
        bookService.edit(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String getBookInfo(Model model, @PathVariable int id){
        var book = bookService.getBookById(id);
        var userId = book.getUserId();
        if(userId != null){
            var user = userService.getUserById(userId);
            model.addAttribute("appUser", user);
        }
        model.addAttribute("book", book);
        return "books/book_page";
    }
////
////    @DeleteMapping("/{id}")
////    public String deleteUser(@PathVariable("id") int id){
////        userService.delete(id);
////        return "redirect:/users";
////    }
//
//    @GetMapping("/fillUsersDb")
//    public String fillUsersTable() {
//        userService.add(new AppUser("Александр", "Зотов", "Андреевич", 1978));
//        userService.add(new AppUser("Виталий", "Мирин", "Александрович", 1988));
//        userService.add(new AppUser("Андрей", "Джугашвили", "Петрович", 1999));
//        userService.add(new AppUser("Сергей", "Ериков", "Сидорович", 2003));
//        userService.add(new AppUser("Евпатий", "Тахто", "Васильевич", 2001));
//        return "users/users_table";
//    }

}
