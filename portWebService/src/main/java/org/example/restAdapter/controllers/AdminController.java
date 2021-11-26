package org.example.restAdapter.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testAdminApi(){
        return "test admin api";
    }
}
