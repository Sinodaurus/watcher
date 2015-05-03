package org.singular.entities;

import javax.persistence.*;

@Entity
@Table(name = "must_watch")
public class MustWatch {
    @Id
    @GeneratedValue
    @Column(name = "must_watch_id")
    private long mustWatchId;
    @OneToOne
    @JoinColumn(name = "watchable")
    private Watchable watchable;
    @OneToOne
    @JoinColumn(name = "user")
    private User user;
    private boolean seen;
    @Column(name = "must_see")
    private boolean mustSee;

    public MustWatch() {}

    public MustWatch(Watchable watchable, User user, boolean seen, boolean mustSee) {
        this.watchable = watchable;
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

    public Watchable getWatchable() {
        return watchable;
    }

    public void setWatchable(Watchable watchable) {
        this.watchable = watchable;
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