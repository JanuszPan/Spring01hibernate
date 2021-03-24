package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Person;
import pl.coderslab.entity.PersonDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PersonDetailsDao {

    @PersistenceContext
    EntityManager entityManager;

    public void create(PersonDetails personDetails){
        entityManager.persist(personDetails);
    }

    public void update(PersonDetails personDetails){
        entityManager.merge(personDetails);
    }

    public PersonDetails read(long id){
        return entityManager.find(PersonDetails.class, id);
    }

    public void delete(PersonDetails personDetails){
        entityManager.remove(entityManager.contains(personDetails) ? personDetails : entityManager.merge(personDetails));
    }
}