package org.singular.entities;

import org.joda.time.DateTime;
import org.singular.entities.dto.person.PersonInfoDTO;

import java.io.Serializable;

public class Token implements Serializable{
    private PersonInfoDTO personInfoDTO;
    private long time;

    public Token(PersonInfoDTO personInfoDTO, long time) {
        this.personInfoDTO = personInfoDTO;
        this.time = time;
    }

    public PersonInfoDTO getPersonInfoDTO() {
        return personInfoDTO;
    }

    public void setPersonInfoDTO(PersonInfoDTO personInfoDTO) {
        this.personInfoDTO = personInfoDTO;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
