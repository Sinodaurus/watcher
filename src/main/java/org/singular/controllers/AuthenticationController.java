package org.singular.controllers;

import org.singular.entities.Token;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AuthenticationController extends Controller{

    @RequestMapping(value = "auth/getUser/{userName}", method = RequestMethod.GET)
    public String userName(@PathVariable String userName) throws IOException {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "auth/{userName}", method = RequestMethod.GET)
    public ResponseEntity authenticate(@RequestHeader("Authorization") String auth, @PathVariable String userName) throws IOException {
        if(auth.equalsIgnoreCase("Basic " + watchService.getPassword(userName))) {
            PersonInfoDTO person = watchService.findPersonByUserName(userName);
            authenticationService.createNewSession(person);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "auth/getToken/{userName}", method = RequestMethod.GET)
    public ResponseEntity getToken(@PathVariable String userName) throws IOException {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        authenticationService.updateSession(person);
        Token userToken = authenticationService.getToken(person);
        if(userToken != null) {
            return new ResponseEntity(authenticationService.createAuthToken(person), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "auth/logout/{userName}", method = RequestMethod.GET)
    public ResponseEntity logout(@PathVariable String userName) throws IOException {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        authenticationService.logout(person);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "auth/updateSession/{userName}", method = RequestMethod.GET)
    public void refreshSession(@PathVariable String userName) {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        authenticationService.updateSession(person);
    }
}
