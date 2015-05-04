package org.singular.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @ManyToMany(targetEntity = Movie.class)
    @Column(name = "seen_movie")
    private List<Movie> seenMovies;

    public User() {}

    public User(String firstName, String lastName, List seenMovies) {
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

    public List getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(List seenMovies) {
        this.seenMovies = seenMovies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }
}