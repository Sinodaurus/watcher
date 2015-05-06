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
    public List<PersonInfoDTO> findAllUsers() {
        List<Person> persons = personRepository.findAll();
        List<PersonInfoDTO> personInfoDTOs = new ArrayList<>();
        for(Person person : persons) {
            personInfoDTOs.add(modelMapper.map(person, PersonInfoDTO.class));
        }
        return personInfoDTOs;
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
            movie.addPerson(person);
            movieRepository.save(movie);
        }
    }
}
