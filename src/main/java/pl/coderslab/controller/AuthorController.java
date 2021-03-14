package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;

@Controller
@RequestMapping(produces = "application/json; charset=UTF-8")
public class AuthorController {
    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @RequestMapping("/author/add")
    @ResponseBody
    public String newAuthor() {
        Author author = new Author();
        author.setFirstName("Name1");
        author.setLastName("Surnname1");
        authorDao.createAuthor(author);
        return "Id nowego autora to: " + author.getId() + " Imię i nazwisko nowego autora to: " + author.getFirstName() + " " + author.getLastName();
    }
    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String getAuthor(@PathVariable long id){
        Author author = authorDao.findAuthorById(id);
        return author.toString();
    }
    @RequestMapping("/author")
    public String updateAuthor(@PathVariable long id, String firstName, String lastName ){
        Author author= authorDao.findAuthorById(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.updateAuthor(author);
        return author.toString();
    }
    @RequestMapping("/author/delete/{id}")
    public String deleteAuthor(@PathVariable long id){
        Author author = authorDao.findAuthorById(id);
        authorDao.deleteAuthor(author);
        return " Author deleted";
    }
}
