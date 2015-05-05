package org.singular.service;

import org.singular.entities.Movie;
import org.singular.entities.User;

import java.util.List;

public interface WatchService {
    public List<Movie> findAllWatchables();
    public void createMustWatch(Movie movie, User user);
}
