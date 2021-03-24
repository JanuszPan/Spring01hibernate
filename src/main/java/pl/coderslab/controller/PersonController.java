package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Person;
import pl.coderslab.entity.PersonDetails;

@Controller
@RequestMapping(produces = "application/json; charset=UTF-8")
public class PersonController {
    PersonDao personDao;
    Person person;
    @RequestMapping("/person/add")
    @ResponseBody
    public String savePerson() {
        Person person =new Person();
        person.setLogin("admin1");
        person.setPassword("pass1");
        person.setEmail("email1");
        return "Id nowego osoby to: " + person.getId() + " Login nowej osoby to: " + person.getLogin();
    }
    @RequestMapping("/person/get/{id}")
    @ResponseBody
    public String getPerson(@PathVariable long id){
        Person person = personDao.read(id);
        return person.toString();
    }
    @RequestMapping("/person/{id}")
    public String updatePerson(@PathVariable long id){
        Person person= personDao.read(id);
        person.setLogin("login1");
        person.setEmail("email1");
        personDao.update(person);
        return person.toString();
    }
    @RequestMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable long id){
        Person author = personDao.read(id);
        personDao.delete(person);

        return "Person deleted";
    }
}
