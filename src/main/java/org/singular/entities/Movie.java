package org.singular.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private long movieId;
    private String title;
    private String year;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String metascore;
    @Column(name = "imdb_rating")
    private String imdbRating;
    @Column(name = "imdb_id", unique = true)
    private String imdbID;
    private String type;
    private String poster;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "seen_movies",
            joinColumns = @JoinColumn (name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> seenByPersons = new HashSet<>();

    public Movie() {}

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {return title; }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    @JsonProperty("Year")
    public void setYear(String year) {
        this.year = year;
    }

    public String getReleased() {
        return released;
    }

    @JsonProperty("Released")
    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    @JsonProperty("Runtime")
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    @JsonProperty("Genre")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    @JsonProperty("Director")
    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    @JsonProperty("Writer")
    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    @JsonProperty("Actors")
    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    @JsonProperty("Plot")
    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    @JsonProperty("Language")
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    @JsonProperty("Country")
    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    @JsonProperty("Awards")
    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMetascore() {
        return metascore;
    }

    @JsonProperty("Metascore")
    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    @JsonProperty("Poster")
    public void setPoster(String poster) {
        this.poster = poster;
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
