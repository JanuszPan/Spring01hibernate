package pl.coderslab.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//bigint auto_increment primary key
    private String title;//  varchar(255) null
    private int rating;//int not null
    private String description;// varchar(255) null

    //    @ManyToOne(cascade = CascadeType.PERSIST)// PERSIST powoduje,że za każdym razem doda publishera
    @ManyToOne
    private Publisher publisher;

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @ManyToMany
    private List<Author> authors = new ArrayList<>();

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}