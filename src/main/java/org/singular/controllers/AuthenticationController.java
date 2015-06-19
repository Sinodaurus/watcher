package org.singular.controllers;

import org.joda.time.DateTime;
import org.singular.entities.Token;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AuthenticationController extends Controller{
    private Token token;

    @RequestMapping(value = "auth/getUser/{userName}", method = RequestMethod.GET)
    public String userName(@PathVariable String userName) throws IOException {
        PersonInfoDTO person = watchService.findPersonByUserName(userName);
        return objectMapper.writeValueAsString(person);
    }

    @RequestMapping(value = "auth/{userName}", method = RequestMethod.GET)
    public ResponseEntity authenticate(@RequestHeader("Authorization") String auth, @PathVariable String userName) throws IOException {
        if(auth.equalsIgnoreCase("Basic " + watchService.getPassword(userName))) {
            createNewSession(userName);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "auth/getToken", method = RequestMethod.GET)
    public ResponseEntity getToken() throws IOException {
        if(token != null && isWithin1Minute(token.getTime())) {
            return new ResponseEntity(objectMapper.writeValueAsString(token), HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "auth/updateSession", method = RequestMethod.POST)
    public void refreshSession(PersonInfoDTO personInfoDTO) {
        updateSession(personInfoDTO);
    }

    private void createNewSession(String userName) {
        PersonInfoDTO personInfoDTO = watchService.findPersonByUserName(userName);
        token = new Token(personInfoDTO, new DateTime().getMillis());
    }

    private void updateSession(PersonInfoDTO personInfoDTO) {
        if(token != null) {
            if(isWithin1Minute(token.getTime())) {
                token.setTime(new DateTime().getMillis());
            }
        }
    }

    private boolean isWithin1Minute(long millis) {
        if(millis > new DateTime().minusMinutes(1).getMillis()) {
            return true;
        } else {
            return false;
        }
    }
}
