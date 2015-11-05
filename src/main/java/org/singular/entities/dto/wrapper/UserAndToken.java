package org.singular.entities.dto.wrapper;

import org.singular.entities.dto.person.PersonInfoDTO;

public class UserAndToken {
    private PersonInfoDTO personInfoDTO;
    private String token;

    public UserAndToken() {}

    public UserAndToken(PersonInfoDTO personInfoDTO, String token) {
        this.personInfoDTO = personInfoDTO;
        this.token = token;
    }

    public PersonInfoDTO getPerson() {
        return personInfoDTO;
    }

    public void setPerson(PersonInfoDTO personInfoDTO) {
        this.personInfoDTO = personInfoDTO;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
