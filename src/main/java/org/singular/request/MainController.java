package org.singular.request;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.singular.entities.MustWatch;
import org.singular.entities.User;
import org.singular.entities.Watchable;
import org.singular.repos.MustWatchRepository;
import org.singular.repos.UserRepository;
import org.singular.repos.WatchableRepository;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WatchService watchService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String all() throws IOException {
        List watchables = watchService.findAllWatchables();
        return objectMapper.writeValueAsString(watchables);
	}

    @RequestMapping(value = "/watchedMovie", method = RequestMethod.POST)
    public ResponseEntity<Watchable> create (@RequestBody Watchable watchable) throws IOException {
//        watchService.createMustWatch(watchable, new User("Sven", "Schit"));
        return new ResponseEntity<Watchable>(watchable, HttpStatus.OK);
    }

//    @RequestMapping(value = "/get", method = RequestMethod.GET)
//    public String get() throws IOException {
//        List mustwatches = mustWatchRepository.findAll();
//        return objectMapper.writeValueAsString(mustwatches);
//    }
}
