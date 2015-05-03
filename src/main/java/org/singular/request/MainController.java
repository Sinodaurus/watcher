package org.singular.request;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;
import org.codehaus.jackson.map.ObjectMapper;
import org.singular.entities.MustWatch;
import org.singular.entities.User;
import org.singular.entities.Watchable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WatchableRepository watchableRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MustWatchRepository mustWatchRepository;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String all() throws IOException {
        List watchables = watchableRepository.findAll();
        return objectMapper.writeValueAsString(watchables);
	}

    @RequestMapping(value = "/put", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody String put() throws IOException {
        Watchable watchable = watchableRepository.findOne(1L);
        User user = userRepository.findOne(1L);
        MustWatch mustWatch = new MustWatch(watchable, user, false, true);
        mustWatchRepository.save(mustWatch);
        return objectMapper.writeValueAsString(mustWatch);
    }
}
