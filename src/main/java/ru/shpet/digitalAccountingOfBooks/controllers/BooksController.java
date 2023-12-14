package ru.shpet.digitalAccountingOfBooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shpet.digitalAccountingOfBooks.dao.BookDAO;
import ru.shpet.digitalAccountingOfBooks.dao.PersonDAO;
import ru.shpet.digitalAccountingOfBooks.models.Book;
import ru.shpet.digitalAccountingOfBooks.models.Person;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("/list")
    public String getBooks(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        return "books/list_of_books";
    }

    @GetMapping("/add_book_form")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add_book";
    }

    @PostMapping("/add_book")
    public String addBook(@ModelAttribute Book book) {
        bookDAO.addBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/{id}")
    public String showBook(Model model, @PathVariable int id, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.findBook(id));
        Optional<Person> bookOwner = bookDAO.getBookOwner(id);

        if (bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else model.addAttribute("people", personDAO.index());

        return "books/show";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id ){
        bookDAO.deleteBook(id);
        return "redirect:/books/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(Model model, @PathVariable int id) {
        model.addAttribute("book", bookDAO.findBook(id));
        return "books/edit_book";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book) {
        bookDAO.updateBook(book, book.getId());
        return "redirect:/books/" + book.getId();
    }

    @PostMapping("/release/{id}")
    public String releaseBook(@PathVariable int id) {
        bookDAO.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @PostMapping("/assign/{id}")
    public String assignBook(@PathVariable int id, @ModelAttribute Person person) {
        bookDAO.assignBook(id, person);
        return "redirect:/books/" + id;
    }
}
