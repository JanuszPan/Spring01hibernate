package pl.coderslab.entity;

import javax.persistence.*;

@Entity
@Table(name="publishers")
public class Publisher {
    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//Utwórz encje o nazwie Publisher.
//Ustal nazwę tabeli bazy danych dla tej encji na publishers.
//Encja ma zawierać następujące pola:
//id
//name
//Utwórz klasę PublisherDao - służącą do operacji na tej encji.
//Utwórz kontroler, realizujący operacje CRUD (create, read, update, delete).