package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Person;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class PersonDao {
    EntityManager entityManager;
    public void savePerson(Person person) {
        entityManager.persist(person);
    }
    public void updatePerson(Person person) {

        entityManager.merge(person);
    }
    public Person findPersonById(long id) {

        return entityManager.find(Person.class, id);
    }

    public void deletePerson(Person person) {
        entityManager.remove(entityManager.contains(person) ? person: entityManager.merge(person));
    }

}
