package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Book;

@Controller
public class BookController {
    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @RequestMapping("/book/add")
    @ResponseBody
    public String newBook() {
        Book book = new Book();
        book.setTitle("Tytuł1");
        book.setRating(3);
        book.setDescription("Description1");
        bookDao.saveBook(book);
        return "Id nowej ksiażki: " + book.getId() + " Tytuł nowej książki to: "+book.getTitle();
    }

    @RequestMapping("/book")
    public String updateBook(@PathVariable long id, String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);
        return book.toString();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id){
  Book book= bookDao.findById(id);
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