package com.personmanager.dao;

import com.personmanager.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

    private static final Logger logger = LoggerFactory.getLogger(PersonDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addPerson(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(person);
        logger.info("Person successfully saved. Person info " + person);
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(person);
        logger.info("Person successfully update. Person info " + person);
    }

    @Override
    @Transactional
    public void removePerson(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person) session.load(Person.class, id);

        if(person != null){
            session.delete(person);
        }
        logger.info("Person successfully removed. Person info " + person);
    }

    @Override
    @Transactional
    public Person getPersonById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person person = (Person) session.load(Person.class, id);
        logger.info("Person successfully loaded. Person info " + person);
        return person;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Person> listPeople() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personList = session.createQuery("from Person").list();
        for(Person person: personList){
            logger.info("Person list " + person);
        }
        return personList;
    }
}
