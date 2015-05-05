package org.singular.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all() throws IOException {
        List users = watchService.findAllUsers();
        return objectMapper.writeValueAsString(users);
    }
}
