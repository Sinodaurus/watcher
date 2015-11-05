package org.singular.service;

import org.singular.entities.dto.movie.MovieInfoDTO;
import org.singular.entities.dto.movie.PersonInfoWithoutMoviesDTO;
import org.singular.entities.dto.person.MovieInfoWithoutPersonsDTO;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.entities.dto.person.PersonInfoWithPasswordDTO;

import java.util.List;

public interface WatchService {
    public PersonInfoDTO createPerson(PersonInfoWithPasswordDTO personInfoWithPasswordDTO);
    public void deletePerson(long id);
    public PersonInfoDTO updatePerson(PersonInfoDTO personInfoDTO);
    public List<MovieInfoDTO> findAllMovies();
    public List<PersonInfoDTO> findAllPersons();
    public PersonInfoDTO findPersonById(long id);
    public MovieInfoWithoutPersonsDTO findMovieById(long id);
    public PersonInfoDTO findPerson(String firstName, String lastName);
    public MovieInfoWithoutPersonsDTO saveMovie(MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO);
    public PersonInfoDTO movieSeenByExistingPerson(long personId, long movieId);
    public void movieSeenByUser(MovieInfoWithoutPersonsDTO movieWithoutPerson, PersonInfoWithoutMoviesDTO personWithoutMovie);
    public void deleteMovieForPerson(long personId, long movieId);
    public String getPassword(String username);
    public PersonInfoDTO findPersonByUserName(String userName);
}