package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    EntityManager entityManager;

    public void createAuthor(Author author) {
        entityManager.persist(author);
    }

    public Author findAuthorById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void updateAuthor(Author author) {
        entityManager.merge(author);
    }

    public void deleteAuthor(Author author) {
        entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }
}
//id
//firstName
//lastName
//Utwórz klasę AuthorDao - służącą do operacji na tej encji.
//Utwórz kontroler, realizujący operacje CRUD (create, read, update, delete).