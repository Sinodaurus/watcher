package org.singular.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "watchables")
public class Movie {

    @Id
    @GeneratedValue
    @Column(name = "watchable_id")
    private long watchableId;
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
    @ManyToMany(targetEntity = User.class)
    @Column(name = "seen_by_user")
    private List<User> users;

    public Movie() {}

    public Movie(String title, String year, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String metascore, String imdbRating, String imdbID, String type, String poster, List<User> users) {
        this.title = title;
        this.year = year;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbID = imdbID;
        this.type = type;
        this.poster = poster;
        this.users = users;
    }

    public long getWatchableId() {
        return watchableId;
    }

    public void setWatchableId(long watchableId) {
        this.watchableId = watchableId;
    }

    public String getTitle() {
        return title;
    }

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
