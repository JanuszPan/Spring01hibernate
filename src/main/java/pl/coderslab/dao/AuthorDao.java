package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Author author){
        entityManager.persist(author);
    }

    public void update(Author author){
        entityManager.merge(author);
    }

    public Author read(long id){
        return entityManager.find(Author.class, id);
    }

    public void delete(Author author){
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }

    public List<Author> findAll(){
        Query query = entityManager.createQuery("select a from Author a");
        List<Author> allAuthors = query.getResultList();
        return allAuthors;
    }
}
//id
//firstName
//lastName
//Utwórz klasę AuthorDao - służącą do operacji na tej encji.
//Utwórz kontroler, realizujący operacje CRUD (create, read, update, delete).