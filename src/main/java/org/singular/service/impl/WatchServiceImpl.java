package org.singular.service.impl;

import org.modelmapper.ModelMapper;
import org.singular.entities.Movie;
import org.singular.entities.Person;
import org.singular.entities.dto.movie.MovieInfoDTO;
import org.singular.entities.dto.movie.PersonInfoWithoutMoviesDTO;
import org.singular.entities.dto.person.MovieInfoWithoutPersonsDTO;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.repos.MovieRepository;
import org.singular.repos.PersonRepository;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WatchServiceImpl implements WatchService{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<MovieInfoDTO> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieInfoDTO> movieInfoDTOs = new ArrayList<>();
        for(Movie movie : movies) {
            movieInfoDTOs.add(modelMapper.map(movie, MovieInfoDTO.class));
        }
        return movieInfoDTOs;
    }

    @Override
    public List<PersonInfoDTO> findAllPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonInfoDTO> personInfoDTOs = new ArrayList<>();
        for(Person person : persons) {
            personInfoDTOs.add(modelMapper.map(person, PersonInfoDTO.class));
        }
        return personInfoDTOs;
    }

    @Override
    public PersonInfoDTO findPersonById(long id) {
        Person person = personRepository.findOne(id);
        PersonInfoDTO personInfoDTO = modelMapper.map(person, PersonInfoDTO.class);
        return personInfoDTO;
    }

    @Override
    public PersonInfoDTO findPerson(String firstName, String lastName) {
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);
        PersonInfoDTO personInfoDTO = modelMapper.map(person, PersonInfoDTO.class);
        return personInfoDTO;
    }

    @Override
    public MovieInfoWithoutPersonsDTO findMovieById(long id) {
        Movie movie = movieRepository.findOne(id);
        MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO = modelMapper.map(movie, MovieInfoWithoutPersonsDTO.class);
        return movieInfoWithoutPersonsDTO;
    }

    @Transactional
    @Override
    public void movieSeenByExistingPerson(long personId, long movieId) {
        Person person = personRepository.findOne(personId);
        Movie movie = movieRepository.findOne(movieId);

        if(movie != null && person != null) {
            movie.addPerson(person);
            person.addMovie(movie);
            movieRepository.save(movie);
            personRepository.save(person);
        }
    }

    @Transactional
    @Override
    public MovieInfoWithoutPersonsDTO saveMovie(MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO) {
        Movie movieToSave = modelMapper.map(movieInfoWithoutPersonsDTO, Movie.class);
        Movie movie = movieRepository.findByTitle(movieToSave.getTitle());
        if(movie != null) {
            return modelMapper.map(movie, MovieInfoWithoutPersonsDTO.class);
        }
        movieRepository.save(movieToSave);
        return modelMapper.map(movieToSave, MovieInfoWithoutPersonsDTO.class);
    }

    @Transactional
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

    @Transactional
    @Override
    public void deleteMovieForPerson(long personId, long movieId) {
        Person person = personRepository.findOne(personId);
        Movie movie = movieRepository.findOne(movieId);
//        Set<Movie> moviesForPerson = new HashSet<>();
//        for(Movie movieForPerson : person.getSeenMovies()) {
//            if(movieForPerson.getMovieId() != movie.getMovieId()) {
//                moviesForPerson.add(movieForPerson);
//            }
//        }
        person.removeMovie(movie);
        movie.removePerson(person);
        personRepository.save(person);
        movieRepository.save(movie);
    }
}
