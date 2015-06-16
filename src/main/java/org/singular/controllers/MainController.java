package org.singular.controllers;

import org.singular.entities.dto.person.PersonInfoDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController extends Controller{

    @RequestMapping(value = "persons/auth/{userName}", method = RequestMethod.GET)
    public String userName(@PathVariable String userName) throws IOException {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "persons/auth/{username}", method = RequestMethod.GET)
    public String authenticate(@RequestHeader("Authorization") String auth, @PathVariable String username) throws IOException {
        if(auth.equalsIgnoreCase("Basic " + watchService.getPassword(username))) {
            return "{\"status\":\"authentified\"}";
        }
        return "{error}";
    }
}
