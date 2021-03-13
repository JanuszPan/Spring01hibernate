package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }
    public void update(Book book) {

        entityManager.merge(book);
    }
    public Book findById(long id) {

        return entityManager.find(Book.class, id);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book: entityManager.merge(book));
    }

}
//Klasa ma realizować podstawowe operacje na encji:
//zapis encji
//edycja encji
//pobieranie po id
//usuwanie po id