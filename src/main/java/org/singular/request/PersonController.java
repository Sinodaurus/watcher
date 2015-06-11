package org.singular.request;

import org.singular.entities.dto.person.PersonInfoDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

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

    @RequestMapping(value = "persons/user/{userName}", method = RequestMethod.GET)
    public String userName(@PathVariable String userName) throws IOException {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "/persons/person/{personId}/deleteMovie/{movieId}", method = RequestMethod.DELETE)
    public void deleteMovieForPerson(@PathVariable long personId, @PathVariable long movieId) throws IOException {
        watchService.deleteMovieForPerson(personId, movieId);
    }

    @RequestMapping(value = "/persons/person/{personId}/watchedMovie/{movieId}", method = RequestMethod.GET)
    public void seenMovieByPerson (@PathVariable long personId, @PathVariable long movieId){
        watchService.movieSeenByExistingPerson(personId, movieId);
    }

    public static void main(String[] args) {
        String base64 = Base64.getEncoder().encodeToString("wing:kalf".getBytes());
        System.out.print(base64);
    }

}
