package org.singular.entities;

import javax.persistence.*;

@Entity
@Table(name = "must_watch")
public class SeenMovie {
    @Id
    @GeneratedValue
    @Column(name = "must_watch_id")
    private long mustWatchId;
    @OneToOne
    @JoinColumn(name = "watchable")
    private Movie movie;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    private boolean seen;
    @Column(name = "must_see")
    private boolean mustSee;

    public SeenMovie() {}

    public SeenMovie(Movie watchable, User user, boolean seen, boolean mustSee) {
        this.movie = watchable;
        this.user = user;
        this.seen = seen;
        this.mustSee = mustSee;
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

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isMustSee() {
        return mustSee;
    }

    public void setMustSee(boolean mustSee) {
        this.mustSee = mustSee;
    }
}