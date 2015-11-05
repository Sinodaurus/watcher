package org.singular.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.singular.entities.Movie;
import org.singular.entities.Person;
import org.singular.entities.dto.movie.PersonInfoWithoutMoviesDTO;
import org.singular.entities.dto.person.MovieInfoWithoutPersonsDTO;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.entities.dto.movie.MovieInfoDTO;
import org.singular.entities.dto.person.PersonInfoWithPasswordDTO;
import org.singular.repos.PersonRepository;
import org.singular.repos.MovieRepository;
import org.singular.service.WatchService;
import org.singular.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class WatchServiceImpl implements WatchService{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public PersonInfoDTO createPerson(PersonInfoWithPasswordDTO personInfoWithPasswordDTO) throws DataIntegrityViolationException{
        Person person = modelMapper.map(personInfoWithPasswordDTO, Person.class);
        personRepository.save(person);
        return modelMapper.map(person, PersonInfoDTO.class);
    }

    @Override
    public void deletePerson(long id) {
        personRepository.delete(id);
    }

    @Override
    public PersonInfoDTO updatePerson(PersonInfoDTO personInfoDTO) {
        Person person = modelMapper.map(personInfoDTO, Person.class);
        personRepository.save(person);
        return personInfoDTO;
    }

    @Override
    public List<MovieInfoDTO> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieInfoDTO> movieInfoDTOs = new ArrayList<>();
        for(Movie movie : movies) {
            movieInfoDTOs.add(modelMapper.map(movie, MovieInfoDTO.class));
        }
        return movieInfoDTOs;
    }

    @Transactional
    @Override
    public List<PersonInfoDTO> findAllPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonInfoDTO> personInfoDTOs = new ArrayList<>();
        for(Person person : persons) {
            personInfoDTOs.add(modelMapper.map(person, PersonInfoDTO.class));
        }
        return personInfoDTOs;
    }

    @Transactional
    @Override
    public PersonInfoDTO findPersonById(long id) {
        Person person = personRepository.findOne(id);
        PersonInfoDTO personInfoDTO = modelMapper.map(person, PersonInfoDTO.class);
        return personInfoDTO;
    }

    @Override
    public MovieInfoWithoutPersonsDTO findMovieById(long id) {
        Movie movie = movieRepository.findOne(id);
        MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO = modelMapper.map(movie, MovieInfoWithoutPersonsDTO.class);
        return movieInfoWithoutPersonsDTO;
    }

    @Override
    public PersonInfoDTO findPerson(String firstName, String lastName) {
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);
        PersonInfoDTO personInfoDTO = modelMapper.map(person, PersonInfoDTO.class);
        return personInfoDTO;
    }

    @Transactional
    @Override
    public MovieInfoWithoutPersonsDTO saveMovie(MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO) {
        Movie movieToSave = modelMapper.map(movieInfoWithoutPersonsDTO, Movie.class);
        Movie movie = movieRepository.findByTitle(movieToSave.getTitle());
        if(movie != null) {
            return modelMapper.map(movie, MovieInfoWithoutPersonsDTO.class);
        }
        if(movieValidated(movieToSave)) {
            movieRepository.save(movieToSave);
        }
        return modelMapper.map(movieToSave, MovieInfoWithoutPersonsDTO.class);
    }

    @Override
    public PersonInfoDTO movieSeenByExistingPerson(long personId, long movieId) {
        Person person = personRepository.findOne(personId);
        Movie movie = movieRepository.findOne(movieId);

        if(movie != null && person != null && !person.getSeenMovies().contains(movie)) {
            movie.addPerson(person);
            person.addMovie(movie);
            personRepository.save(person);
        }
        return modelMapper.map(person, PersonInfoDTO.class);
    }

    @Override
    public void movieSeenByUser(MovieInfoWithoutPersonsDTO movieWithoutPerson, PersonInfoWithoutMoviesDTO personWithoutMovie) {
        Movie movie = modelMapper.map(movieWithoutPerson, Movie.class);
        Person person = modelMapper.map(personWithoutMovie, Person.class);

        Movie movieToUpdate = movieRepository.findByTitle(movie.getTitle());
        Person personToUpdate = personRepository.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
        if(movieToUpdate != null) {
            if(personToUpdate != null) {
                movieToUpdate.addPerson(personToUpdate);
            } else {
                movieToUpdate.addPerson(person);
            }
            movieRepository.save(movieToUpdate);
        } else {
            if(personToUpdate != null) {
                movie.addPerson(personToUpdate);
            } else {
                movie.addPerson(person);
            }
            movieRepository.save(movie);
        }
    }

    @Override
    public void deleteMovieForPerson(long personId, long movieId) {
        Person person = personRepository.findOne(personId);
        Movie movie = movieRepository.findOne(movieId);
        person.removeMovie(movie);
        movie.removePerson(person);
        movieRepository.save(movie);
        personRepository.save(person);
    }

    @Override
    public String getPassword(String username) {
        Person person = personRepository.findByUsername(username);
        if(person != null) {
            return Utils.encodeB64(person.getUsername() + ":" + person.getPassword());
        } else {
            return null;
        }
    }

    @Override
    public PersonInfoDTO findPersonByUserName(String userName) {
        Person person = personRepository.findByUsername(userName);
        if(person != null) {
            return modelMapper.map(person, PersonInfoDTO.class);
        } else {
            return null;
        }
    }

    private boolean movieValidated(Movie movie) {
        if(StringUtils.isNotEmpty(movie.getTitle()) &&
                StringUtils.isNotEmpty(movie.getImdbID())) {
            return true;
        }
        return false;
    }
}
