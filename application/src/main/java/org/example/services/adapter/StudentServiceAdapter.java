package org.example.services.adapter;

import org.example.domain.student.StudentPersistencePort;
import org.example.domain.student.models.Student;
import org.example.services.ports.StudentService;

import java.util.List;

public class StudentServiceAdapter implements StudentService {
    final StudentPersistencePort studentPersistencePort;

    public StudentServiceAdapter(StudentPersistencePort studentPersistencePort) {
        this.studentPersistencePort = studentPersistencePort;
    }

    public List<Student> getAllStudent() {
        return studentPersistencePort.getAllStudent();
    }
}
