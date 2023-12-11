package ru.shpet.digitalAccountingOfBooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BooksController {
    @GetMapping("/list")
    public String getBooks() {
        return null;
    }
}
