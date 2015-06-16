package org.singular.controllers;

import org.singular.entities.dto.person.PersonInfoDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class PersonController extends Controller{

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String allUsers() throws IOException {
        List persons = watchService.findAllPersons();
        return objectMapper.writeValueAsString(persons);
    }

    @RequestMapping(value = "/persons/person/{id}", method = RequestMethod.GET)
    public String singleUser(@PathVariable long id) throws IOException {
        PersonInfoDTO person = watchService.findPersonById(id);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "persons/findPerson/{firstName}/{lastName}", method = RequestMethod.GET)
    public String findUser(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        PersonInfoDTO person = watchService.findPerson(firstName, lastName);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "/persons/person/{personId}/deleteMovie/{movieId}", method = RequestMethod.DELETE)
    public void deleteMovieForPerson(@PathVariable long personId, @PathVariable String imdbMovieId) throws IOException {
        watchService.deleteMovieForPerson(personId, imdbMovieId);
    }

    @RequestMapping(value = "/persons/person/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public String seenMovieByPerson (@PathVariable long id, @RequestBody String imdbMovieId) throws IOException {
        PersonInfoDTO personInfoDTO = watchService.movieSeenByExistingPerson(id, imdbMovieId);
//        return new ResponseEntity<Person>(person,HttpStatus.OK);
        return objectMapper.writeValueAsString(personInfoDTO);
    }

    public static void main(String[] args) {
        String base64 = Base64.getEncoder().encodeToString("wing:kalf".getBytes());
        System.out.print(base64);
    }
}
