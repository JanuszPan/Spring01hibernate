package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PublisherDao {
    @PersistenceContext
    EntityManager entityManager;

    public void createPublisher(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public Publisher findPublisherById(long id) {
        return entityManager.find(Publisher.class, id);
    }

    public void updatePublisher(Publisher publisher) {
        entityManager.merge(publisher);
    }

    public void deletePublisher(Publisher publisher) {
        entityManager.remove(entityManager.contains(publisher) ? publisher : entityManager.merge(publisher));
    }
}
