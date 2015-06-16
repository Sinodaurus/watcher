package org.singular.repos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.singular.entities.Person;
import org.singular.entities.SeenMovie;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {
    private Person person;
    private SeenMovie seenMovie;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private PersonRepository personRepository;

    @Mock
    private SeenMovieRepository seenMovieRepository;

    @Before
    public void before() {
        person = new Person();
        seenMovie = new SeenMovie();
        seenMovie.setSeenMovieId(1);
        seenMovie.setImdbMovieId("tt0061852");
        person.setPersonId(1);
        person.setFirstName("Sven");
        person.setLastName("Schittecatte");

        person.setSeenMovies(Sets.newHashSet(seenMovie));

        Mockito.when(personRepository.findOne(1L)).thenReturn(person);
        Mockito.when(seenMovieRepository.findOne(1L)).thenReturn(seenMovie);
    }

    @Test
    public void addMovieTouserTest() {
        Person person = personRepository.findOne(1L);

        SeenMovie movie = new SeenMovie();
        movie.setSeenMovieId(2);
        movie.setImdbMovieId("tt002454");

        person.addMovie(movie);
        personRepository.save(person);
    }
}
