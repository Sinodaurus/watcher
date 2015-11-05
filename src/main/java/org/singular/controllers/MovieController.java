package org.singular.controllers;

import org.singular.entities.dto.movie.MovieInfoDTO;
import org.singular.entities.dto.person.MovieInfoWithoutPersonsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController extends Controller {
    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public String allMovies() throws IOException {
        List<MovieInfoDTO> movieInfoDTOs = watchService.findAllMovies();
        return objectMapper.writeValueAsString(movieInfoDTOs);
    }

    @RequestMapping(value = "/movie/{id}", method = RequestMethod.GET)
    public String singleMovie(@PathVariable long id) throws IOException {
        MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO = watchService.findMovieById(id);
        return objectMapper.writeValueAsString(movieInfoWithoutPersonsDTO);
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<MovieInfoWithoutPersonsDTO> create (@RequestBody MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO){
        MovieInfoWithoutPersonsDTO returnedMovie = watchService.saveMovie(movieInfoWithoutPersonsDTO);
        return new ResponseEntity<MovieInfoWithoutPersonsDTO>(returnedMovie, HttpStatus.OK);
    }
}