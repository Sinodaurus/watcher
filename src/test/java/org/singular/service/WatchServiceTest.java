package org.singular.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.repos.MovieRepository;
import org.singular.repos.PersonRepository;
import org.singular.service.impl.WatchServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class WatchServiceTest {
//    private Person person1 = new Person();
//    private Person person2 = new Person();
//    private SeenMovie seenMovie1 = new SeenMovie();
//    private SeenMovie seenMovie2 = new SeenMovie();

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private WatchService watchService = new WatchServiceImpl();

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MovieRepository movieRepository;

    @Before
    public void before() {
//        seenMovie1.setSeenMovieId(1);
//        seenMovie1.setImdbMovieId("tt0061852");
//        seenMovie2.setSeenMovieId(2);
//        seenMovie2.setImdbMovieId("ss4548789");
//        person1.setPersonId(1);
//        person1.setFirstName("Sven");
//        person1.setLastName("Schittecatte");
//        person2.setPersonId(1);
//        person2.setFirstName("Alix");
//        person2.setLastName("Goossens");
//
//        seenMovie1.addPerson(person1);
//        seenMovie1.addPerson(person2);
//        seenMovie2.addPerson(person1);
//        seenMovie2.addPerson(person2);
//        person1.setSeenMovies(Sets.newHashSet(seenMovie1, seenMovie2));
//        person2.setSeenMovies(Sets.newHashSet(seenMovie1, seenMovie2));
//
//        Mockito.when(personRepository.findAll()).thenReturn(Lists.newArrayList(person1, person2));
//        Mockito.when(personRepository.findOne(1L)).thenReturn(person1);
//
//        MovieInfoDTO movieInfoDTO = new MovieInfoDTO("testMovieId");
//        SeenMovie seenMovie = modelMapper.map(movieInfoDTO, SeenMovie.class);
//        Mockito.when(movieRepository.findByImdbMovieId("testMovieId")).thenReturn(seenMovie);
    }

    @Test
    public void findAllPersonsTest() {
//        List<PersonInfoDTO> persons = watchService.findAllPersons();
//        assertTrue(persons.size() == 2);

        //Testing if there is no recursion
//        String objectToString = persons.get(0).toString();
//        assertTrue(objectToString.contains("tt0061852"));
//        assertTrue(objectToString.contains("ss4548789"));
//        assertTrue(objectToString.contains("Sven"));
//        assertTrue(objectToString.contains("Schittecatte"));
    }

    @Test
    public void findPersonByIdTest() {
//        PersonInfoDTO personInfoDTO = watchService.findPersonById(1);
//        assertEquals(personInfoDTO.getFirstName(), "Sven");
//        assertEquals(personInfoDTO.getSeenMovies().size(), 2);
        //get 1 person.  But with no persons in SeenMovies
    }

    @Test
    public void findPersonTest() {
        //find person by name.  But with no persons in SeenMovies
    }

    @Test
    public void movieSeenByExistingPersonTest() {
//        PersonInfoDTO personInfoDTO = watchService.movieSeenByExistingPerson(1, "testMovieId");
//        assertEquals(personInfoDTO.getSeenMovies().size(), 3);
        //create SeenMovie with person.
        //add movie to person.
        //return person.  But with no persons in SeenMovies
    }

    @Test
    public void deleteMovieForPersonTest() {
        //delete movie from person.
        //return person.  But with no persons in SeenMovies
    }

    @Test
    public void getPasswordTest() {
        //return base64 encoded password of person found by username
    }

    @Test
    public void findPersonByUserNameTest() {
        //find person by username.
    }
}
