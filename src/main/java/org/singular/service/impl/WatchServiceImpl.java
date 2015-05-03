package org.singular.service.impl;

import org.singular.entities.MustWatch;
import org.singular.entities.User;
import org.singular.entities.Watchable;
import org.singular.repos.MustWatchRepository;
import org.singular.repos.UserRepository;
import org.singular.repos.WatchableRepository;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchServiceImpl implements WatchService{
    @Autowired
    private WatchableRepository watchableRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MustWatchRepository mustWatchRepository;

    @Override
    public List<Watchable> findAllWatchables() {
        return watchableRepository.findAll();
    }

    @Override
    public void createMustWatch(Watchable watchable, User user) {
        watchableRepository.save(watchable);
        userRepository.save(user);
        MustWatch mustWatch = new MustWatch(watchable, user, false, true);
        mustWatchRepository.save(mustWatch);
    }
}
