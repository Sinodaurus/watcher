package org.singular.entities.dto.person;

import java.util.HashSet;
import java.util.Set;

public class PersonInfoWithPasswordDTO {
    private long personId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Set<MovieInfoWithoutPersonsDTO> seenMovies = new HashSet<>();

    public PersonInfoWithPasswordDTO() {}

    public PersonInfoWithPasswordDTO(long personId, String firstName, String lastName) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MovieInfoWithoutPersonsDTO> getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(Set<MovieInfoWithoutPersonsDTO> seenMovies) {
        this.seenMovies = seenMovies;
    }
}