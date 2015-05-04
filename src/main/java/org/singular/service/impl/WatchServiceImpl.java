package org.singular.service.impl;

import org.hibernate.exception.JDBCConnectionException;
import org.singular.entities.Movie;
import org.singular.entities.SeenMovie;
import org.singular.entities.User;
import org.singular.repos.SeenMoviesRepository;
import org.singular.repos.UserRepository;
import org.singular.repos.WatchableRepository;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class WatchServiceImpl implements WatchService{
    @Autowired
    private WatchableRepository watchableRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeenMoviesRepository seenMoviesRepository;

    @Override
    public List<Movie> findAllWatchables() {
        return watchableRepository.findAll();
    }

    @Override
    public List<SeenMovie> findAllSeenMovies() {
        return seenMoviesRepository.findAll();
    }

    @Override
    public void createMustWatch(Movie movie, User user) {
        watchableRepository.save(movie);
//        if(userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName()) == null) {
//            userRepository.save(user);
//        }
        SeenMovie seenMovie = new SeenMovie(movie, user);
        seenMoviesRepository.save(seenMovie);
    }
}
