package ru.backend.UserService.services.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.UserService.model.Book;
import ru.backend.UserService.repository.BookRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @Override
    public void add(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBooksList(Integer pageNumber, Integer pageSize, Boolean sortByYearOfBook) {
        String sortAttr = sortByYearOfBook ? "year" : "id";
        Sort sort = Sort.by(Sort.Direction.ASC, sortAttr);
        if(pageNumber != null & pageSize != null){
            Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
            return bookRepository.findAll(pageable).getContent();
        }
        else{
            return bookRepository.findAll(sort);
        }
    }

    @Override
    public List<Book> getBooksByUserId(int userId) {
        return bookRepository.findAllByUserId(userId)
                .stream()
                .peek(book -> book.setExpired(checkExpired(book.getDateOfAttach())))
                .toList();
    }

    @Override
    public List<Book> getBooksStartingWith(String query) {
        return bookRepository.findByTitleStartingWithIgnoreCase(query);
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void edit(Book newBook) {
        bookRepository.save(newBook);
    }

    private boolean checkExpired(Date bookAttachedDate){
        if(bookAttachedDate == null){
            return false;
        }
        LocalDate attachedLocalDate = bookAttachedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ChronoUnit.DAYS.between(attachedLocalDate, LocalDate.now()) > 10;
    }
}
