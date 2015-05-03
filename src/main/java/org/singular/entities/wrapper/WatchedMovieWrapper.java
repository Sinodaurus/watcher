package org.singular.entities.wrapper;

import org.singular.entities.User;
import org.singular.entities.Movie;

public class WatchedMovieWrapper {
    private Movie watchable;
    private User user;

    public WatchedMovieWrapper() {}

    public WatchedMovieWrapper(Movie watchable, User user) {

        this.watchable = watchable;
        this.user = user;
    }

    public Movie getWatchable() {
        return watchable;
    }

    public void setWatchable(Movie watchable) {
        this.watchable = watchable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
