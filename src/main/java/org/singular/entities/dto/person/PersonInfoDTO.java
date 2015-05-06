package org.singular.entities.dto.person;

import java.util.HashSet;
import java.util.Set;

public class PersonInfoDTO {
    private long personId;
    private String firstName;
    private String lastName;
    private Set<MovieInfoWithoutPersonsDTO> seenMovies = new HashSet<>();

    public PersonInfoDTO() {}

    public PersonInfoDTO(String firstName, String lastName) {
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

    public Set<MovieInfoWithoutPersonsDTO> getSeenMovies() {
        return seenMovies;
    }

    public void setSeenMovies(Set<MovieInfoWithoutPersonsDTO> seenMovies) {
        this.seenMovies = seenMovies;
    }
}
