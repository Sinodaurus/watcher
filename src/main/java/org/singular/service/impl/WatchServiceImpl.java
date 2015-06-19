package org.singular.service.impl;

import org.modelmapper.ModelMapper;
import org.singular.entities.Person;
import org.singular.entities.SeenMovie;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.entities.dto.movie.SeenMovieDTO;
import org.singular.repos.PersonRepository;
import org.singular.repos.SeenMovieRepository;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class WatchServiceImpl implements WatchService{
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private SeenMovieRepository seenMovieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    @Override
    public List<PersonInfoDTO> findAllPersons() {
        List<Person> persons = personRepository.findAll();
        List<PersonInfoDTO> personInfoDTOs = new ArrayList<>();
        for(Person person : persons) {
            personInfoDTOs.add(modelMapper.map(person, PersonInfoDTO.class));
        }
        return personInfoDTOs;
    }

    @Transactional
    @Override
    public PersonInfoDTO findPersonById(long id) {
        Person person = personRepository.findOne(id);
        PersonInfoDTO personInfoDTO = modelMapper.map(person, PersonInfoDTO.class);
        return personInfoDTO;
    }

    @Override
    public PersonInfoDTO findPerson(String firstName, String lastName) {
        Person person = personRepository.findByFirstNameAndLastName(firstName, lastName);
        PersonInfoDTO personInfoDTO = modelMapper.map(person, PersonInfoDTO.class);
        return personInfoDTO;
    }

    @Transactional
    @Override
    public PersonInfoDTO movieSeenByExistingPerson(long personId, String imdbMovieId) {
        Person person = personRepository.findOne(personId);
        SeenMovie seenMovie = seenMovieRepository.findByImdbMovieId(imdbMovieId);

        if(seenMovie == null) {
            SeenMovieDTO seenMovieDTO = new SeenMovieDTO(imdbMovieId);
            seenMovie = modelMapper.map(seenMovieDTO, SeenMovie.class);
            seenMovieRepository.save(seenMovie);
        }

        if(person != null) {
            person.addMovie(seenMovie);
            seenMovie.addPerson(person);
        }

        PersonInfoDTO personInfoDTO = modelMapper.map(person, PersonInfoDTO.class);

        return personInfoDTO;
    }

    @Transactional
    @Override
    public void deleteMovieForPerson(long personId, String imdbMovieId) {
        Person person = personRepository.findOne(personId);
        SeenMovie seenMovie = seenMovieRepository.findByImdbMovieId(imdbMovieId);
        person.removeMovie(seenMovie);
        personRepository.save(person);
    }

    @Override
    public String getPassword(String username) {
        Person person = personRepository.findByUsername(username);
        if(person != null) {
            return Base64.getEncoder().encodeToString((person.getUsername() + ":" + person.getPassword()).getBytes());
        } else {
            return null;
        }
    }

    @Override
    public PersonInfoDTO findPersonByUserName(String userName) {
        Person person = personRepository.findByUsername(userName);
        if(person != null) {
            return modelMapper.map(person, PersonInfoDTO.class);
        } else {
            return null;
        }
    }
}
