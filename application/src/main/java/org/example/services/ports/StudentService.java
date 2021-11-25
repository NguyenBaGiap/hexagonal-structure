package org.example.services.ports;

import org.example.domain.student.models.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
}
