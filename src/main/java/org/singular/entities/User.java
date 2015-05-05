package org.singular.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @JsonBackReference
    @ManyToMany(mappedBy = "seenByUsers", fetch = FetchType.EAGER)
    private Set<Movie> seenMovies = new HashSet<>();

    public User() {}

    public User(String firstName, String lastName, Set seenMovies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.seenMovies = seenMovies;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public Set getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(Set seenMovies) {
        this.seenMovies = seenMovies;
    }
}