package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    EntityManager entityManager;

    public void create(Publisher publisher){
        entityManager.persist(publisher);
    }

    public void update(Publisher publisher){
        entityManager.merge(publisher);
    }

    public Publisher read(long id){
        return entityManager.find(Publisher.class, id);
    }

    public void delete(Publisher publisher){
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }

    public List<Publisher> findAll(){
        Query query = entityManager.createQuery("select p from Publisher p");
        List<Publisher> allPublishers = query.getResultList();
        return allPublishers;
    }
}
