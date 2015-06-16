package org.singular.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private long personId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String username;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "seenByPersons", fetch = FetchType.EAGER)
    private Set<SeenMovie> seenMovies = new HashSet<>();

    public Person() {}

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<SeenMovie> getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(Set<SeenMovie> seenMovies) {
        this.seenMovies = seenMovies;
    }

    public void addMovie(SeenMovie movieId) {
        this.seenMovies.add(movieId);
    }

    public void removeMovie(SeenMovie movieId) {
        this.seenMovies.remove(movieId);
    }
}