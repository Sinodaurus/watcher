package org.singular.service;

import org.singular.entities.User;
import org.singular.entities.Watchable;

import java.util.List;

public interface WatchService {
    public List<Watchable> findAllWatchables();
    public void createMustWatch(Watchable watchable, User user);
}
