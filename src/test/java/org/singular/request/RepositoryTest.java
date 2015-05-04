package org.singular.request;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.singular.Application;
import org.singular.entities.Movie;
import org.singular.entities.SeenMovie;
import org.singular.entities.User;
import org.singular.repos.SeenMoviesRepository;
import org.singular.repos.UserRepository;
import org.singular.repos.WatchableRepository;
import org.springframework.test.context.ContextConfiguration;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = Application.class)
public class RepositoryTest {
    @Mock
    private WatchableRepository watchableRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SeenMoviesRepository seenMoviesRepository;

    @Before
    public void before() {
        Movie goodMovie = new Movie(
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
                "http://imgSource.com/img.jpg");
        User user = new User("Sven", "Schittecatte", Lists.newArrayList());
        Mockito.when(watchableRepository.findOne(1L)).thenReturn(goodMovie);
        Mockito.when(userRepository.findOne(1L)).thenReturn(user);

    }

    @Test
    public void testMakeMustWatch() {
        Movie movie = watchableRepository.findOne(1L);
        User user = userRepository.findOne(1L);
        seenMoviesRepository.save(new SeenMovie(movie, user));
    }
}
