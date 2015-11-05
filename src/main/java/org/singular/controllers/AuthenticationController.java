package org.singular.controllers;

import org.singular.entities.Token;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.entities.dto.wrapper.UserAndToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController extends Controller{

    @RequestMapping(value = "auth/{userName}", method = RequestMethod.GET)
    public ResponseEntity userName(@RequestHeader("Authorization") String token, @PathVariable String userName) throws IOException {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        if(authenticationService.isAllegible(person, token)) {
            return new ResponseEntity(objectMapper.writeValueAsString(person), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "auth/login/{userName}", method = RequestMethod.GET)
    public ResponseEntity authenticate(@RequestHeader("Authorization") String auth, @PathVariable String userName) throws IOException {
        if(auth.equalsIgnoreCase("Basic " + watchService.getPassword(userName))) {
            PersonInfoDTO personInfoDTO = watchService.findPersonByUserName(userName);
            authenticationService.createNewSession(personInfoDTO);
            UserAndToken userAndToken = new UserAndToken(personInfoDTO, authenticationService.createAuthToken(personInfoDTO));
            return new ResponseEntity(userAndToken, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "auth/getToken/{userName}", method = RequestMethod.GET)
    public ResponseEntity getToken(@PathVariable String userName) throws IOException {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        authenticationService.updateSession(person);
        Token userToken = authenticationService.getToken(person);
        if(userToken != null) {
            return new ResponseEntity(person, HttpStatus.OK);
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
