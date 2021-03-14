package pl.coderslab.entity;

import javax.persistence.*;

@Entity
@Table(name="personDetails")
public class PersonDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String streetNumber;
    private String street;
    private String city;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
//Utwórz klasę PersonDetails zawierającą pola:
//id
//firstName
//lastName
//streetNumber
//street
//city
//Połącz encje za pomocą relacji @OneToOne.
//Utwórz kontroler, realizujący operacje CRUD (create, read, update, delete).