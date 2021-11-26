package org.example.restAdapter.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/private")
public class PrivateController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testPrivateController(){
        return "test private controller";
    }
}
