package org.singular.request;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MainController extends Controller{

    @RequestMapping(value = "/auth/{username}", method = RequestMethod.GET)
    public String authenticate(@RequestHeader("Authorization") String auth, @PathVariable String username) throws IOException {
        if(auth.equalsIgnoreCase("Basic " + watchService.getPassword(username))) {
            return "{\"status\":\"authentified\"}";
        }
        return "{error}";
    }
}
