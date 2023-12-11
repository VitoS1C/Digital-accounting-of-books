package ru.shpet.digitalAccountingOfBooks.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//TODO
public class Book {
    private int id;

    @NotNull(message = "Название книги не может быть пустым!")
    @Size(min = 2, max = 50, message = "Название книги должно быть от 2 до 50 символов!")
    private String title;

    @Min(value = 0, message = "Год выпуска не может быть меньше 1")
     private int year;

    public Book() {
    }

    public Book(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
