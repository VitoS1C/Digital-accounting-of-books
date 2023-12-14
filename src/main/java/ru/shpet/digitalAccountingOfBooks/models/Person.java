package ru.shpet.digitalAccountingOfBooks.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Имя не может быть пустым. Введите ФИО!")
    @Size(min = 2, max = 50, message = "Длина должна быть от 2 до 50 символов!")
    private String fullName;
    @Min(value = 0, message = "Год не может быть меньше 0")
    private int birthYear;

    public Person() {

    }

    public Person(int id, String name, int birthYear) {
        this.id = id;
        this.fullName = name;
        this.birthYear = birthYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }
}
