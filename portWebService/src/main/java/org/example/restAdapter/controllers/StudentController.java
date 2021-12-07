package org.example.restAdapter.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.auth.StudentUserDetails;
import org.example.domain.student.models.Student;
import org.example.restAdapter.dto.request.StudentRegisterDTO;
import org.example.restAdapter.dto.response.PageDTO;
import org.example.restAdapter.dto.response.StudentRegisterResponse;
import org.example.restAdapter.exception.BusinessException;
import org.example.restAdapter.sercurity.JwtTokenProvider;
import org.example.services.ports.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/public")
@Validated
public class StudentController {
    final StudentService studentService;
    final JwtTokenProvider jwtTokenProvider;

    final AuthenticationManager authenticationManager;

    public StudentController(StudentService studentService, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.studentService = studentService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public PageDTO<Student> getStudents() {
        return new PageDTO<Student>(studentService.getAllStudent(), 100L, 1L, 10L);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public StudentRegisterResponse createStudent(@RequestBody @Valid StudentRegisterDTO request) throws BusinessException {
        try {
            Student model = Student
                    .builder()
                    .email(request.getEmail())
                    .mobileNumber(request.getMobileNumber())
                    .build();
            Student saved = studentService.createStudent(model);
            String jwt = JwtTokenProvider.generateTokenForStudent(saved.getEmail(), saved.getMobileNumber(), saved.getId());
            return StudentRegisterResponse
                    .builder()
                    .id(saved.getId())
                    .email(saved.getEmail())
                    .mobileNumber(saved.getMobileNumber())
                    .accessToken(jwt)
                    .roles(saved.getRoles().stream().map(Enum::name).collect(Collectors.toList()))
                    .build();
        } catch (Exception exception) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Error");
        }
    }
}
