package org.singular.service;

import org.singular.entities.dto.movie.MovieInfoDTO;
import org.singular.entities.dto.movie.PersonInfoWithoutMoviesDTO;
import org.singular.entities.dto.person.MovieInfoWithoutPersonsDTO;
import org.singular.entities.dto.person.PersonInfoDTO;

import java.util.List;

public interface WatchService {
    public List<MovieInfoDTO> findAllMovies();
    public List<PersonInfoDTO> findAllPersons();
    public PersonInfoDTO findPersonById(long id);
    public MovieInfoWithoutPersonsDTO findMovieById(long id);
    public PersonInfoDTO findPerson(String firstName, String lastName);
    public void movieSeenByExistingPerson(long personId, long movieId);
    public MovieInfoWithoutPersonsDTO saveMovie(MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO);
    public void movieSeenByUser(MovieInfoWithoutPersonsDTO movieWithoutPerson, PersonInfoWithoutMoviesDTO personWithoutMovie);
    public void deleteMovieForPerson(long personId, long movieId);
}
