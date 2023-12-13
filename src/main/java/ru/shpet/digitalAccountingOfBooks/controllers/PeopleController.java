package ru.shpet.digitalAccountingOfBooks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shpet.digitalAccountingOfBooks.dao.PersonDAO;
import ru.shpet.digitalAccountingOfBooks.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/list")
    public String getPeople(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/list_of_people";
    }

    @GetMapping("/add_person")
    public String showAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "people/add_person";
    }

    @PostMapping("/save")
    public String addPerson(@ModelAttribute Person person) {
        personDAO.save(person);
        return "redirect:/people/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable int id) {
        personDAO.delete(id);
        return "redirect:/people/list";
    }

    @GetMapping("/show_update/{id}")
    public String showEditForm(Model model, @PathVariable int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit_person";
    }

    @PostMapping("/edit")
    public String editPerson(@ModelAttribute Person person) {
        personDAO.update(person.getId(),person);
        return "redirect:/people/" + person.getId();
    }
}
