package org.example.restAdapter.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.auth.StudentUserDetails;
import org.example.domain.student.models.Student;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/private")
public class PrivateController {
    final AuthenticationManager authenticationManager;

    public PrivateController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testPrivateController(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student studentLogin = ((StudentUserDetails) principal).getStudent();
        log.info("student login: {}", studentLogin);
        return "test private controller";
    }
}
