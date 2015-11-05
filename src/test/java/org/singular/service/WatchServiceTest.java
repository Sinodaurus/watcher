package org.singular.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.singular.entities.Movie;
import org.singular.entities.Person;
import org.singular.entities.dto.movie.MovieInfoDTO;
import org.singular.entities.dto.person.PersonInfoDTO;
import org.singular.repos.MovieRepository;
import org.singular.repos.PersonRepository;
import org.singular.service.impl.WatchServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class WatchServiceTest {
    private Person person1 = new Person();
    private Person person2 = new Person();
    private Movie seenMovie1 = new Movie();
    private Movie seenMovie2 = new Movie();

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    private WatchService watchService = new WatchServiceImpl();

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MovieRepository movieRepository;

    @Before
    public void before() {
        seenMovie1.setMovieId(1);
        seenMovie1.setImdbID("tt0061852");
        seenMovie2.setMovieId(2);
        seenMovie2.setImdbID("ss4548789");
        person1.setPersonId(1);
        person1.setFirstName("Sven");
        person1.setLastName("Schittecatte");
        person2.setPersonId(1);
        person2.setFirstName("Alix");
        person2.setLastName("Goossens");

        seenMovie1.addPerson(person1);
        seenMovie1.addPerson(person2);
        seenMovie2.addPerson(person1);
        seenMovie2.addPerson(person2);
        person1.setSeenMovies(Sets.newHashSet(seenMovie1, seenMovie2));
        person2.setSeenMovies(Sets.newHashSet(seenMovie1, seenMovie2));

        Mockito.when(personRepository.findAll()).thenReturn(Lists.newArrayList(person1, person2));
        Mockito.when(personRepository.findOne(1L)).thenReturn(person1);

        MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
        movieInfoDTO.setMovieId(3);
        movieInfoDTO.setImdbID("ss012365");
        Movie seenMovie3 = modelMapper.map(movieInfoDTO, Movie.class);
        Mockito.when(movieRepository.findOne(1L)).thenReturn(seenMovie1);
        Mockito.when(movieRepository.findOne(2L)).thenReturn(seenMovie2);
        Mockito.when(movieRepository.findOne(3L)).thenReturn(seenMovie3);
    }

    @Ignore
    @Test
    public void findAllPersonsTest() {
        List<PersonInfoDTO> persons = watchService.findAllPersons();
        assertTrue(persons.size() == 2);

//        Testing if there is no recursion
        String objectToString = persons.get(0).toString();
        assertTrue(objectToString.contains("tt0061852"));
        assertTrue(objectToString.contains("ss4548789"));
        assertTrue(objectToString.contains("Sven"));
        assertTrue(objectToString.contains("Schittecatte"));
    }

    @Test
    public void findPersonByIdTest() {
        PersonInfoDTO personInfoDTO = watchService.findPersonById(1);
        assertEquals(personInfoDTO.getFirstName(), "Sven");
        assertEquals(personInfoDTO.getSeenMovies().size(), 2);
//        get 1 person.  But with no persons in SeenMovies
    }

    @Test
    public void findPersonTest() {
//        find person by name.  But with no persons in SeenMovies
    }

    @Test
    public void movieSeenByExistingPersonTest() {
        PersonInfoDTO personInfoDTO = watchService.findPersonById(1L);
        assertEquals(personInfoDTO.getSeenMovies().size(), 2);
//        create SeenMovie with person.
//        add movie to person.
//        return person.  But with no persons in SeenMovies
    }

    @Test
    public void deleteMovieForPersonTest() {
        watchService.deleteMovieForPerson(1, 1);
        PersonInfoDTO personInfoDTO = watchService.findPersonById(1L);
        assertEquals(personInfoDTO.getSeenMovies().size(), 1);
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
