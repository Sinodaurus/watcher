package org.singular.request;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.singular.Application;
import org.singular.entities.Movie;
import org.singular.entities.User;
import org.singular.repos.UserRepository;
import org.singular.repos.WatchableRepository;
import org.springframework.test.context.ContextConfiguration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Application.class)
public class RepositoryTest {
    private Movie goodMovie;
    private User user;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private WatchableRepository watchableRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void before() {
        goodMovie = new Movie(
                "Interstellar",
                "2014",
                "2014",
                "150",
                "sci-fi",
                "Christopher Nolan", // make director object
                "Christopher Nolan",
                "Mcanaughy, Girl",
                "They die.",
                "English",
                "America",
                "Oscars",
                "8.9",
                "9.0",
                "25214",
                "type",
                "http://imgSource.com/img.jpg",
                Sets.newHashSet());
        user = new User("Sven", "Schittecatte", Sets.newHashSet());
        Mockito.when(watchableRepository.findOne(1L)).thenReturn(goodMovie);
        Mockito.when(userRepository.findOne(1L)).thenReturn(user);

    }

    @Test
    public void testMakeMustWatch() {
        Movie movie = watchableRepository.findOne(1L);
        User user = userRepository.findOne(1L);
    }

    @Test
    public void testJsonInfiniteLoop() throws IOException {
        goodMovie.setSeenByUsers(Sets.newHashSet(user));
        user.setSeenMovies(Sets.newHashSet(goodMovie));
        String result = objectMapper.writeValueAsString(goodMovie);
        assertThat(result, containsString("Interstellar"));
        assertThat(result, containsString("Sven"));
        assertThat(result, containsString("seenByUsers"));

    }
}
