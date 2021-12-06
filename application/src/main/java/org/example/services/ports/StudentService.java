package org.example.services.ports;

import org.example.domain.student.models.Student;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface StudentService extends UserDetailsService {
    List<Student> getAllStudent();
    Student createStudent(Student student);
    Student findByEmail(String email);
}
