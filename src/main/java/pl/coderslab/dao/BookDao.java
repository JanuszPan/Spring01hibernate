package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;//zarządca encji https://docs.jboss.org/hibernate/core/4.0/devguide/en-US/html/ch03.html

    public void create(Book book){
        entityManager.persist(book);//zapisuje encje do bazy danych (jako parametr przyjmuje obiekt do zapisu)
    }

    public void update(Book book){

        entityManager.merge(book);
    }

    public Book read(long id){

        return entityManager.find(Book.class, id);
    }

    public void delete(Book book){
        //encję można usunąć tylko taką, która jest zarządzana przez entityManager. Jeżeli jest zarządzana
        //usuwamy, jeżeli nie łączymy z entityManager za pomoca metody merge
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    public List<Book> findAll(){
        Query query = entityManager.createQuery("SELECT b FROM Book b");
        List<Book> allBooks = query.getResultList();
        return allBooks;
    }

    public List<Book> getRatingList(int rating){
        Query query = entityManager.createQuery("SELECT b from Book b where b.rating=:rating");
        query.setParameter("rating", rating);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllWithNonemptyPublisher(){
        Query query = entityManager.createQuery("select b from Book b join b.publisher");
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllByPublisher(Publisher publisher){
        Query query = entityManager.createQuery("select b from Book b where b.publisher=:publisher");
        query.setParameter("publisher", publisher);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllByAuthor(Author author){
        Query query = entityManager.createQuery("select b from Book b where :author member of b.authors");
        query.setParameter("author", author);
        List<Book> books = query.getResultList();
        return books;
    }

}
//Klasa ma realizować podstawowe operacje na encji:
//zapis encji
//edycja encji
//pobieranie po id
//usuwanie po id