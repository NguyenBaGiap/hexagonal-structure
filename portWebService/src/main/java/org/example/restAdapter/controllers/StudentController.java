package org.example.restAdapter.controllers;

import org.example.domain.student.models.Student;
import org.example.restAdapter.dto.response.PageDTO;
import org.example.services.ports.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public PageDTO<Student> getStudents() {
        List<Student> students = studentService.getAllStudent();
        return new PageDTO<Student>(students, 100L, 1L, 10L);
    }
}
