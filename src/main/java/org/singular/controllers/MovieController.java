package org.singular.controllers;

import org.singular.entities.dto.movie.MovieInfoDTO;
import org.singular.entities.dto.person.MovieInfoWithoutPersonsDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController extends Controller {
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public String allMovies() throws IOException {
        List<MovieInfoDTO> movieInfoDTOs = watchService.findAllMovies();
        return objectMapper.writeValueAsString(movieInfoDTOs);
    }

    @RequestMapping(value = "/movies/movie/{id}", method = RequestMethod.GET)
    public String singleMovie(@PathVariable long id) throws IOException {
        MovieInfoWithoutPersonsDTO movieInfoWithoutPersonsDTO = watchService.findMovieById(id);
        return objectMapper.writeValueAsString(movieInfoWithoutPersonsDTO);
    }
}