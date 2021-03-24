package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;

import java.util.List;

@Controller
@RequestMapping(value = "/authors", produces = "text/html;charset=utf-8")
public class AuthorController {

    private AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @RequestMapping(value = "/add/{firstName}/{lastName}")
    @ResponseBody
    public String saveAuthor(@PathVariable String firstName, @PathVariable String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.create(author);
        return "Utworzono " + author.toString();
    }

    @RequestMapping(value = "/update/{id:\\d+}/{firstName}/{lastName}")
    @ResponseBody
    public String editAuthor(@PathVariable long id, @PathVariable String firstName, @PathVariable String lastName) {
        Author author = authorDao.read(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.update(author);
        return author.toString();
    }

    @RequestMapping(value = "/read/{id:\\d+}")
    @ResponseBody
    public String findAuthor(@PathVariable long id) {
        return authorDao.read(id).toString();
    }

    @RequestMapping(value = "/delete/{id:\\d+}")
    @ResponseBody
    public String deleteAuthor(@PathVariable long id) {
        Author author = authorDao.read(id);
        authorDao.delete(author);
        return "deleted";
    }

    @RequestMapping("/all")
    @ResponseBody
    public String findAllAuthors() {
        List<Author> authorList = authorDao.findAll();
        return authorList.toString();
    }
}