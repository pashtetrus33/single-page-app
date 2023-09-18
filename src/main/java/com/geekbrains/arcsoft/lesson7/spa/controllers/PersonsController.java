package com.geekbrains.arcsoft.lesson7.spa.controllers;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;
import com.geekbrains.arcsoft.lesson7.spa.models.SearchModel;
import com.geekbrains.arcsoft.lesson7.spa.services.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/persons")
public class PersonsController {

    BusinessService<Person> personsBusinessService;

    PersonsController(BusinessService<Person> personsBusinessService) {
        this.personsBusinessService = personsBusinessService;
    }

    @GetMapping
    public String showAllPersons(Model model) {
        model.addAttribute("persons", personsBusinessService.getAll());
        return "printPersons";
    }

    @GetMapping("/add")
    public String showPersonAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "addNewPersonForm";
    }

    @PostMapping("/add")
    public String addPerson(Person person, BindingResult bindingResult, Model model) {
        personsBusinessService.add(person);
        model.addAttribute("persons", personsBusinessService.getAll());
        return "printPersons";
    }

    @GetMapping("/search")
    public String showSearchForm(Model model) {
        model.addAttribute("searchModel", new SearchModel());
        return "searchForm";
    }

    @PostMapping("/search")
    public String searchPerson(SearchModel searchModel, BindingResult bindingResult, Model model) {

        model.addAttribute("persons", personsBusinessService.search(searchModel.getSearchTerm()));
        return "printPersons";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        model.addAttribute("persons", personsBusinessService.getAll());
        return "personsAdmin";
    }

    @PostMapping("/editForm")
    public String showEditForm(Person person, Model model) {

        model.addAttribute("person", person);
        return "editForm";
    }

    @PostMapping("/doUpdate")
    public String updatePerson(Person person, BindingResult bindingResult, Model model, Long id) {
        personsBusinessService.update(person.getId(), person);
        model.addAttribute("persons", personsBusinessService.getAll());
        return "personsAdmin";
    }

    @PostMapping("/delete/")
    public String deletePerson(Person person, BindingResult bindingResult, Model model) {
        personsBusinessService.delete(person.getId());
        model.addAttribute("persons", personsBusinessService.getAll());
        return "personsAdmin";
    }

    @GetMapping("/spa")
    public String showSPAPage() {
        return "personsSPA";
    }
}
