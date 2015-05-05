package org.singular.service.impl;

import com.google.common.collect.Sets;
import org.singular.entities.Movie;
import org.singular.entities.User;
import org.singular.repos.UserRepository;
import org.singular.repos.WatchableRepository;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WatchServiceImpl implements WatchService{
    @Autowired
    private WatchableRepository watchableRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public List<Movie> findAllWatchables() {
        List<Movie> watchables = watchableRepository.findAll();
        return watchables;
    }

    @Transactional
    @Override
    public void createMustWatch(Movie movie, User user) {
        movie.setUsers(Sets.newHashSet(user));
        user.setSeenMovies(Sets.newHashSet(movie));
        watchableRepository.save(movie);
        userRepository.save(user);
//        if(userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName()) == null) {
//            userRepository.save(user);
//        }
    }
}
