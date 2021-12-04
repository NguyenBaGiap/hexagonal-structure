package org.example.restAdapter.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.student.models.Student;
import org.example.restAdapter.dto.request.StudentRegisterDTO;
import org.example.restAdapter.dto.response.PageDTO;
import org.example.services.ports.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/public")
@Validated
public class StudentController {
    final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public PageDTO<Student> getStudents() {
        return new PageDTO<Student>(studentService.getAllStudent(), 100L, 1L, 10L);
    }

    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public Student createStudent(@RequestBody @Valid StudentRegisterDTO request) {
        Student model = Student.builder().email(request.getEmail()).mobileNumber(request.getMobileNumber()).build();
        return studentService.createStudent(model);
    }
}
