package org.singular.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "seenByPersons", fetch = FetchType.EAGER)
    private Set<Movie> seenMovies = new HashSet<>();

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

    public Set<Movie> getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(Set<Movie> seenMovies) {
        this.seenMovies = seenMovies;
    }

    public void addMovie(Movie movie) {
        this.seenMovies.add(movie);
    }
}