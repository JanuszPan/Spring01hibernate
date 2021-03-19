package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;//zarządca encji
    public List<Book> findAll(){
        Query query = entityManager.createQuery("select b from  Book b");
        return query.getResultList();
    }
    public List<Book> getRatingList(int rating) {
        Query ratingListQuery = entityManager.createQuery("select b from Book b where b.rating = :rating");
        ratingListQuery.setParameter("rating", rating);
        return ratingListQuery.getResultList();
    }
    public void saveBook(Book book) {
        entityManager.persist(book);//zapisuje encje do bazy danych (jako parametr przyjmuje obiekt do zapisu)
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