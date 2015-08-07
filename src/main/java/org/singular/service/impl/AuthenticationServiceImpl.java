package org.singular.service.impl;

import org.joda.time.DateTime;
import org.singular.entities.Token;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.service.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private Map<PersonInfoDTO, Token> tokens = new HashMap<>();

    @Override
    public void createNewSession(PersonInfoDTO person) {
        if(tokens.get(person) == null) {
            tokens.put(person, new Token(new DateTime().getMillis()));
        } else {
            updateSession(person);
        }
    }

    @Override
    public void updateSession(PersonInfoDTO person) {
        Token userToken = tokens.get(person);
        if(userToken != null) {
            if(isWithin2Minute(userToken.getTime())) {
                userToken.setTime(new DateTime().getMillis());
                tokens.put(person, userToken);
            }
        }
    }

    @Override
    public void logout(PersonInfoDTO person) {
        tokens.remove(person);
    }

    @Override
    public Token getToken(PersonInfoDTO person) {
        return tokens.get(person);
    }

    @Override
    public String createAuthToken(PersonInfoDTO person) {
        return person.getFirstName() + tokens.get(person).getTime();
    }

    private boolean isWithin2Minute(long millis) {
        if(millis > new DateTime().minusMinutes(2).getMillis()) {
            return true;
        } else {
            return false;
        }
    }
}
