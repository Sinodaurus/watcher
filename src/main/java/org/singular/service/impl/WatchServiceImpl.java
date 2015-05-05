package org.singular.service.impl;

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

    @Override
    public List<Movie> findAllWatchables() {
        List<Movie> watchables = watchableRepository.findAll();
        return watchables;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
