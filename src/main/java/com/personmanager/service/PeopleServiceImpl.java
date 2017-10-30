package com.personmanager.service;

import com.personmanager.dao.PersonDao;
import com.personmanager.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {

    private PersonDao personDao;

    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void addPerson(Person person) {
        this.personDao.addPerson(person);
    }

    @Override
    public void updatePerson(Person person) {
        this.personDao.updatePerson(person);
    }

    @Override
    public void removePerson(int id) {
        this.personDao.removePerson(id);
    }

    @Override
    public Person getPersonById(int id) {
        return this.personDao.getPersonById(id);
    }

    @Override
    public List<Person> listPeople() {
        return this.personDao.listPeople();
    }
}
