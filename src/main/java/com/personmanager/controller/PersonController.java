package com.personmanager.controller;

import com.personmanager.model.Person;
import com.personmanager.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private PeopleService peopleService;

    @Autowired
    @Qualifier(value = "peopleService")
    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "people", method = RequestMethod.GET)
    public String listPersons(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("listPeople", this.peopleService.listPeople());
        return "people";
    }

    @RequestMapping(value = "/people/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person){
        if(person.getId() == 0){
            this.peopleService.addPerson(person);
        }else {
            this.peopleService.updatePerson(person);
        }

        return "redirect:/people";
    }

    @RequestMapping("/remove/{personid}")
    public String removePerson(@PathVariable("personid") int id){
        this.peopleService.removePerson(id);

        return "redirect:/people";
    }

    @RequestMapping("/edit/{personid}")
    public String editPerson(@PathVariable("personid") int id, Model model){
        model.addAttribute("person", this.peopleService.getPersonById(id));
        model.addAttribute("listPeople", this.peopleService.listPeople());

        return "people";
    }

    @RequestMapping("persondata/{personid}")
    public String personData(@PathVariable("personid") int id, Model model){
        model.addAttribute("person", this.peopleService.getPersonById(id));
        return "persondata";
    }
}
