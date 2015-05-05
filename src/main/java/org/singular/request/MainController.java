package org.singular.request;

import org.codehaus.jackson.map.ObjectMapper;
import org.singular.entities.wrapper.WatchedMovieWrapper;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class MainController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WatchService watchService;

	@RequestMapping(value = "/allKnown", method = RequestMethod.GET)
	public String allKnown() throws IOException {
        List watchables = watchService.findAllWatchables();
        return objectMapper.writeValueAsString(watchables);
	}

    @RequestMapping(value = "/watchedMovie", method = RequestMethod.POST)
    public ResponseEntity<WatchedMovieWrapper> create (@RequestBody WatchedMovieWrapper watchedMovieWrapper) {
        watchService.createMustWatch(watchedMovieWrapper.getWatchable(), watchedMovieWrapper.getUser());
        return new ResponseEntity<WatchedMovieWrapper>(watchedMovieWrapper, HttpStatus.OK);
    }

//    @RequestMapping(value = "/allSeen", method = RequestMethod.GET)
//    public String allSeen() throws IOException {
//        List mustwatches = watchService.findAllSeenMovies();
//        return objectMapper.writeValueAsString(mustwatches);
//    }
}
