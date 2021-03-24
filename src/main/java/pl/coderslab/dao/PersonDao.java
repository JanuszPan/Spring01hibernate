package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDao {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Person person){
        entityManager.persist(person);
    }

    public void update(Person person){
        entityManager.merge(person);
    }

    public Person read(long id){
        return entityManager.find(Person.class, id);
    }

    public void delete(Person person){
        entityManager.remove(entityManager.contains(person) ? person : entityManager.merge(person));
    }
}