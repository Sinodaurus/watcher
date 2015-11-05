package org.singular.service;

import org.singular.entities.Token;
import org.singular.entities.dto.person.PersonInfoDTO;

public interface AuthenticationService {
    public void createNewSession(PersonInfoDTO person);
    public void updateSession(PersonInfoDTO person);
    public boolean isAllegible(PersonInfoDTO person, String token);
    public void logout(PersonInfoDTO person);
    public Token getToken(PersonInfoDTO person);
    public String createAuthToken(PersonInfoDTO person);
}
