package org.singular.controllers;

import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.entities.dto.person.PersonInfoWithPasswordDTO;
import org.singular.entities.dto.wrapper.UserAndToken;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class PersonController extends Controller{

    //Person

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String allUsers() throws IOException {
        List persons = watchService.findAllPersons();
        return objectMapper.writeValueAsString(persons);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public String singleUser(@PathVariable long id) throws IOException {
        PersonInfoDTO person = watchService.findPersonById(id);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable long id) throws IOException {
        PersonInfoDTO person = watchService.findPersonById(id);
        watchService.updatePerson(person);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable long id) throws IOException {
        watchService.deletePerson(id);
        return ResponseEntity.ok("deleted");
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity createUser(@RequestBody PersonInfoWithPasswordDTO personInfoWithPasswordDTO) throws IOException {
        PersonInfoDTO personInfoDTO;
        UserAndToken userAndToken;
        try {
            personInfoDTO = watchService.createPerson(personInfoWithPasswordDTO);
            authenticationService.createNewSession(personInfoDTO);
            userAndToken = new UserAndToken(personInfoDTO, authenticationService.createAuthToken(personInfoDTO));
        } catch(DataIntegrityViolationException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userAndToken);
    }

    @RequestMapping(value = "person/{firstName}/{lastName}", method = RequestMethod.GET)
    public String findUser(@PathVariable String firstName, @PathVariable String lastName) throws IOException {
        PersonInfoDTO person = watchService.findPerson(firstName, lastName);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "/person/{personId}/{movieId}", method = RequestMethod.DELETE)
    public void deleteMovieForPerson(@PathVariable long personId, @PathVariable long movieId) throws IOException {
        watchService.deleteMovieForPerson(personId, movieId);
    }

    @RequestMapping(value = "/person/{personId}/{movieId}", method = RequestMethod.PUT)
    public String seenMovieByPerson (@PathVariable long personId, @PathVariable long movieId) throws IOException {
        PersonInfoDTO personInfoDTO = watchService.movieSeenByExistingPerson(personId, movieId);
        return objectMapper.writeValueAsString(personInfoDTO);
    }

    public static void main(String[] args) {
        String base64 = Base64.getEncoder().encodeToString("wing:kalf".getBytes());
        System.out.print(base64);
    }
}
