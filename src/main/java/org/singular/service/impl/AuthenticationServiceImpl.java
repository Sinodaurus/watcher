package org.singular.service.impl;

import org.joda.time.DateTime;
import org.singular.entities.Token;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.service.AuthenticationService;
import org.singular.util.Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private Map<Long, Token> tokens = new HashMap<>();

    @Override
    public void createNewSession(PersonInfoDTO person) {
        if(tokens.get(person.getPersonId()) == null) {
            tokens.put(person.getPersonId(), new Token(new DateTime().getMillis()));
        } else {
            updateSession(person);
        }
    }

    @Override
    public void updateSession(PersonInfoDTO person) {
        Token userToken = tokens.get(person.getPersonId());
        if(userToken != null) {
            userToken.setTime(new DateTime().getMillis());
            tokens.put(person.getPersonId(), userToken);
        }
    }

    @Override
    public boolean isAllegible(PersonInfoDTO person, String token) {
        String tokenNamePart = token.substring(0, token.indexOf("-"));
        tokenNamePart = Utils.decodeB64(tokenNamePart);
        long tokenTimePart = Long.parseLong(token.substring(token.indexOf("-")+1));
        Token userToken = tokens.get(person.getPersonId());
        if(userToken != null) {
            if(isWithin2Minute(tokenTimePart) && tokenNamePart.equalsIgnoreCase(person.getFirstName())) {
                userToken.setTime(new DateTime().getMillis());
                tokens.put(person.getPersonId(), userToken);
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout(PersonInfoDTO person) {
        tokens.remove(person.getPersonId());
    }

    @Override
    public Token getToken(PersonInfoDTO person) {
        return tokens.get(person.getPersonId());
    }

    @Override
    public String createAuthToken(PersonInfoDTO person) {
        return Utils.encodeB64(person.getFirstName()) + "-" + tokens.get(person.getPersonId()).getTime();
    }

    private boolean isWithin2Minute(long millis) {
        if(millis > new DateTime().minusMinutes(2).getMillis()) {
            return true;
        } else {
            return false;
        }
    }
}
