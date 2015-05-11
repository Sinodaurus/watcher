package org.singular.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.singular.entities.Movie;
import org.singular.entities.Person;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.entities.dto.wrapper.WatchedMovieWrapper;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WatchService watchService;

	@RequestMapping(value = "/allMovies", method = RequestMethod.GET)
	public String allMovies() throws IOException {
        List movies = watchService.findAllMovies();
        return objectMapper.writeValueAsString(movies);
	}

    @RequestMapping(value = "/allPersons", method = RequestMethod.GET)
    public String allUsers() throws IOException {
        List persons = watchService.findAllPersons();
        return objectMapper.writeValueAsString(persons);
    }

    @RequestMapping(value = "/findPerson/{firstName}/{lastName}", method = RequestMethod.GET)
    public String findUser(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        PersonInfoDTO person = watchService.findPerson(firstName, lastName);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "/watchedMovie", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<WatchedMovieWrapper> create (@RequestBody WatchedMovieWrapper watchedMovieWrapper){
            watchService.movieSeenByUser(watchedMovieWrapper.getMovie(), watchedMovieWrapper.getPerson());
            return new ResponseEntity<WatchedMovieWrapper>(watchedMovieWrapper, HttpStatus.OK);
        }

    @RequestMapping(value = "/helpPerson", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Person> helpUser (@RequestBody Person person){
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/helpMovie", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Movie> helpMovie (@RequestBody Movie movie){
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }
}
