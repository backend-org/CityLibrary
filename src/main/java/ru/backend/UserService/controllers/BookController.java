package ru.backend.UserService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.model.Book;
import ru.backend.UserService.services.book.BookService;
import ru.backend.UserService.services.user.UserService;

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

    @GetMapping("/new")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add_book_form";
    }

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

    @PatchMapping("/detach")
    public String detachBook(@RequestParam("bookId") int bookId) {
        Book book = bookService.getBookById(bookId);
        book.setUserId(null);
        editBook(book);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/attach")
    public String attachBookToUser(@ModelAttribute("attachingUser") AppUser appUser, @RequestParam("bookId") int bookId){
        Book book = bookService.getBookById(bookId);
        book.setUserId(appUser.getId());
        editBook(book);
        return "redirect:/books/" + bookId;
    }


    @GetMapping("/{id}")
    public String getBookInfo(Model model, @PathVariable int id){
        var book = bookService.getBookById(id);
        model.addAttribute("book", book);

        var userId = book.getUserId();
        if(userId != null){
            var user = userService.getUserById(userId);
            model.addAttribute("appUser", user);
        }
        else{
            model.addAttribute("emptyUserForSelector", new AppUser());
            model.addAttribute("usersList", userService.listUsers());
        }
        return "books/book_page";
    }
}
