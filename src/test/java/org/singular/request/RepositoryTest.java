package org.singular.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.singular.Application;
import org.singular.entities.Movie;
import org.singular.entities.Person;
import org.singular.entities.dto.movie.MovieInfoDTO;
import org.singular.repos.MovieRepository;
import org.singular.repos.PersonRepository;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Application.class)
public class RepositoryTest {
    private Movie goodMovie;
    private Person person;
    private ObjectMapper objectMapper = new ObjectMapper();
    private ModelMapper modelMapper = new ModelMapper();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void before() {
        goodMovie = new Movie();
        person = new Person();
        goodMovie.setTitle("Interstellar");
        person.setFirstName("Sven");
        goodMovie.addPerson(person);
        person.addMovie(goodMovie);

        Mockito.when(movieRepository.findOne(1L)).thenReturn(goodMovie);
        Mockito.when(personRepository.findOne(1L)).thenReturn(person);

    }

    @Test
    public void testMakeMustWatch() {
        Movie movie = movieRepository.findOne(1L);
        Person person = personRepository.findOne(1L);
    }

    @Test
    public void testJsonInfiniteLoop() throws IOException {
        MovieInfoDTO movie = modelMapper.map(goodMovie, MovieInfoDTO.class);
        String result = objectMapper.writeValueAsString(movie);
        assertThat(result, containsString("Interstellar"));
        assertThat(result, containsString("Sven"));
        assertThat(result, containsString("seenByPersons"));
    }

    @Test
    public void testSaveMovieSeen() {
        movieRepository.save(goodMovie);
        personRepository.save(person);
    }
}
