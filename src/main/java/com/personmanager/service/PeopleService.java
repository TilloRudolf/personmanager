package com.personmanager.service;

import com.personmanager.model.Person;

import java.util.List;

public interface PeopleService {
    public void addPerson(Person person);
    public void updatePerson(Person person);
    public void removePerson(int id);
    public Person getPersonById(int id);
    public List<Person> listPeople();
}
