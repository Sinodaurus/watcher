package org.singular.entities.dto.wrapper;

import org.singular.entities.dto.movie.PersonInfoWithoutMoviesDTO;
import org.singular.entities.dto.person.MovieInfoWithoutPersonsDTO;

public class WatchedMovieWrapper {
    private MovieInfoWithoutPersonsDTO movie;
    private PersonInfoWithoutMoviesDTO person;

    public WatchedMovieWrapper() {}

    public WatchedMovieWrapper(MovieInfoWithoutPersonsDTO movie, PersonInfoWithoutMoviesDTO person) {

        this.movie = movie;
        this.person = person;
    }

    public MovieInfoWithoutPersonsDTO getMovie() {
        return movie;
    }

    public void setMovie(MovieInfoWithoutPersonsDTO movie) {
        this.movie = movie;
    }

    public PersonInfoWithoutMoviesDTO getPerson() {
        return person;
    }

    public void setPerson(PersonInfoWithoutMoviesDTO person) {
        this.person = person;
    }
}