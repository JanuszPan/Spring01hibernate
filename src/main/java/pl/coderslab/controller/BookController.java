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


@Controller
@RequestMapping(produces = "application/json; charset=UTF-8")
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
//    private static final System.Logger logger = (System.Logger) LoggerFactory.getLogger(BookController.class);

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {

        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }


    @RequestMapping("/book/add")
    @ResponseBody
    public String newBook() {
        Book book = new Book();

        book.setTitle("Tytuł1");
        book.setRating(3);
        book.setDescription("Description1");

//        book.setTitle("Tytuł2");
//        book.setRating(4);
//        book.setDescription("Description2");
//
//        book.setTitle("Tytuł3");
//        book.setRating(5);
//        book.setDescription("Description3");

//zadanie1
        Publisher publisher = new Publisher();
        publisher.setName("publisher1");
        publisherDao.createPublisher(publisher);
        book.setPublisher(publisher);
//zadanie2
        List<Author> authors = new ArrayList<>();
        Author author1 = authorDao.findAuthorById(1);
        Author author2 = authorDao.findAuthorById(2);
        authors.add(author1);
        authors.add(author2);
        book.setAuthors(authors);

        bookDao.saveBook(book);
        return "Id nowej ksiażki: " + book.getId() + " Tytuł nowej książki to: " + book.getTitle() + " rating nowej książki to: "+book.getRating()+ " Opis nowej książki to: "+book.getDescription();
    }

    @RequestMapping("/book/all")
    @ResponseBody
    public String findAllBooks() {
        List<Book> books = bookDao.findAll();
//        books.forEach(b -> logger.info(b.toString()));
        return Objects.nonNull(books) ? books.toString() : "Brak książek";
    }
    @GetMapping(path = "/book/rating", produces = "text/plain;charset=UTF-8")
    String getRatingList(@RequestParam("rating") int rating) {
        List<Book> books = bookDao.getRatingList(rating);
        return books.toString();
    }
    @RequestMapping("/book/{id}/{title}")
    public String updateBook(@PathVariable long id, @PathVariable String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
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