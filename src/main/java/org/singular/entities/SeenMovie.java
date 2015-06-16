package org.singular.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class SeenMovie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private long seenMovieId;
    @Column(name = "imdb_movie_id")
    private String imdbMovieId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "seen_movies",
            joinColumns = @JoinColumn (name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> seenByPersons = new HashSet<>();

    public SeenMovie() {}

    public long getSeenMovieId() {
        return seenMovieId;
    }

    public void setSeenMovieId(long seenMovieId) {
        this.seenMovieId = seenMovieId;
    }

    public String getImdbMovieId() {
        return imdbMovieId;
    }

    public void setImdbMovieId(String imdbMovieId) {
        this.imdbMovieId = imdbMovieId;
    }

    public Set<Person> getSeenByPersons() {
        return seenByPersons;
    }

    public void setSeenByPersons(Set<Person> seenByPersons) {
        this.seenByPersons = seenByPersons;
    }

    public void addPerson(Person person) {
        this.seenByPersons.add(person);
    }

    public void removePerson(Person person) {
        this.seenByPersons.remove(person);
    }
}
