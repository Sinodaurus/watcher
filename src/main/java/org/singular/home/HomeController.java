package org.singular.home;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WatchableRepository watchableRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String all() throws IOException {
        List watchables = watchableRepository.findAll();
        return objectMapper.writeValueAsString(watchables);
	}
}
