package org.singular.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.singular.service.AuthenticationService;
import org.singular.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class Controller {
    protected ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected WatchService watchService;

    @Autowired
    protected AuthenticationService authenticationService;
}
