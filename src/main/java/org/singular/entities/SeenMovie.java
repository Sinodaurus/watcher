package org.singular.entities;

import javax.persistence.*;

@Entity
@Table(name = "seen_movies")
public class SeenMovie {
    @Id
    @GeneratedValue
    @Column(name = "must_watch_id")
    private long mustWatchId;
    @Column(name = "watchable")
    private Movie movie;
    private User user;

    public SeenMovie() {}

    public SeenMovie(Movie watchable, User user) {
        this.movie = watchable;
        this.user = user;
    }

    public long getMustWatchId() {
        return mustWatchId;
    }

    public void setMustWatchId(long mustWatchId) {
        this.mustWatchId = mustWatchId;
    }

    public Movie getWatchable() {
        return movie;
    }

    public void setWatchable(Movie watchable) {
        this.movie = watchable;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}