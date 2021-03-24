package pl.coderslab.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping(produces = "application/json; charset=UTF-8")
public class BookController {
    private static final String BOOK_NOT_FOUND_MESSAGE="Nie znaleziono książki o podanym Id";
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
//    private static final System.Logger logger = (System.Logger) LoggerFactory.getLogger(BookController.class);

    public BookController(final BookDao bookDao, final PublisherDao publisherDao, final AuthorDao authorDao) {// wstrzykujemy Dao

        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }
//POST
    @PostMapping("/book/add")
    @ResponseBody
    public String newBook(
            @RequestParam("title") String title,
            @RequestParam("rating") int rating,
            @RequestParam("description") String description) {

        Book book = new Book();

        book.setTitle(title);
        book.setRating(rating);
        book.setDescription(description);

//zadanie1
        Publisher publisher = new Publisher();
        publisher.setName("publisher1");
        publisherDao.create(publisher);
        book.setPublisher(publisher);
//zadanie2
        List<Author> authors = new ArrayList<>();
        Author author1 = authorDao.read(1);
        Author author2 = authorDao.read(2);
        authors.add(author1);
        authors.add(author2);
        book.setAuthors(authors);

        bookDao.create(book);//wywołanie metody utworzonej w klasie BookDao
        // do obiektu pobieramy nadany podczas zapisu identyfikator id
        return "Id nowej książki: " + book.getId() + " Tytuł nowej książki to: " + book.getTitle() + " rating nowej książki to: "+book.getRating()+ " Opis nowej książki to: "+book.getDescription();
    }
    //GET
    @GetMapping(path = "/book/get")
    @ResponseBody
    public String getBook(@RequestParam("id") long id) {
        Book book = bookDao.read(id);
        return Objects.nonNull(book)? book.toString(): BOOK_NOT_FOUND_MESSAGE;
    }

    @GetMapping(path = "/book/rating", produces = "text/plain;charset=UTF-8")
    String getRatingList(@RequestParam("rating") int rating) {
        List<Book> books = bookDao.getRatingList(rating);
        return books.toString();
    }
    @PutMapping("/book}")
    public String updateBook(@RequestParam("id") long id, @RequestParam("title") String newTitle) {
        Book book = bookDao.read(id);
        if(Objects.isNull(book)){
            return BOOK_NOT_FOUND_MESSAGE;
        }
        book.setTitle(newTitle);
        bookDao.update(book);
        return book.toString();
    }
    @GetMapping(path = "/book/all")
    @ResponseBody
    public String findAllBooks() {
        List<Book> books = bookDao.findAll();
//        books.forEach(b -> logger.info(b.toString()));
        return Objects.nonNull(books) ? books.toString() : BOOK_NOT_FOUND_MESSAGE;
    }
    //DELETE /book/delete?id=1
    @DeleteMapping("/book/delete")
    @ResponseBody
    public String deleteBook(@RequestParam("id") long id) {
        Book book = bookDao.read(id);
        if(Objects.isNull(book)){
            return BOOK_NOT_FOUND_MESSAGE;
        }
        bookDao.delete(book);
        return " Book deleted";
    }



}
//Utwórz kontroler o nazwie BookController.
//Utwórz akcje kontrolera, które będą wykonywać następujące operacje:
//zapis encji
//edycja encji
//pobieranie
//usuwanie
//Wszystkie dane potrzebne do wykonania operacji mogą być zaszyte w kodzie akcji.