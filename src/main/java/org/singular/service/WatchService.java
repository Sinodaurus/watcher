package org.singular.service;

import org.singular.entities.dto.person.PersonInfoDTO;

import java.util.List;

public interface WatchService {
    public List<PersonInfoDTO> findAllPersons();
    public PersonInfoDTO findPersonById(long id);
    public PersonInfoDTO findPerson(String firstName, String lastName);
    public PersonInfoDTO movieSeenByExistingPerson(long personId, String imdbMovieId);
    public void deleteMovieForPerson(long personId, String imdbMovieId);
    public String getPassword(String username);
    public PersonInfoDTO findPersonByUserName(String userName);
}