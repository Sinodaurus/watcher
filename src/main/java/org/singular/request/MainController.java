package org.singular.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.singular.entities.Movie;
import org.singular.entities.Person;
import org.singular.entities.dto.wrapper.WatchedMovieWrapper;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WatchService watchService;

	@RequestMapping(value = "/allKnown", method = RequestMethod.GET)
	public String allKnown() throws IOException {
        List movies = watchService.findAllMovies();
        return objectMapper.writeValueAsString(movies);
	}

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() throws IOException {
        List users = watchService.findAllUsers();
        return objectMapper.writeValueAsString(users);
    }

    @RequestMapping(value = "/watchedMovie", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<WatchedMovieWrapper> create (@RequestBody WatchedMovieWrapper watchedMovieWrapper){
            watchService.movieSeenByUser(watchedMovieWrapper.getMovie(), watchedMovieWrapper.getPerson());
            return new ResponseEntity<WatchedMovieWrapper>(watchedMovieWrapper, HttpStatus.OK);
        }

    @RequestMapping(value = "/helpUser", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Person> helpUser (@RequestBody Person person){
//        watchService.movieSeenByUser(watchedMovieWrapper.getWatchable(), watchedMovieWrapper.getUser());
        return new ResponseEntity<Person>(person, HttpStatus.OK);
    }

    @RequestMapping(value = "/helpMovie", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Movie> helpMovie (@RequestBody Movie movie){
//        watchService.movieSeenByUser(watchedMovieWrapper.getWatchable(), watchedMovieWrapper.getUser());
        return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }
}
