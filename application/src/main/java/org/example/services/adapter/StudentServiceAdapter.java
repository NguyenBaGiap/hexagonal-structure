package org.example.services.adapter;

import org.example.domain.auth.StudentUserDetails;
import org.example.domain.student.StudentPersistencePort;
import org.example.domain.student.models.Student;
import org.example.services.ports.StudentService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class StudentServiceAdapter implements StudentService {
    final StudentPersistencePort studentPersistencePort;

    public StudentServiceAdapter(StudentPersistencePort studentPersistencePort) {
        this.studentPersistencePort = studentPersistencePort;
    }

    public List<Student> getAllStudent() {
        return studentPersistencePort.getAllStudent();
    }

    public Student createStudent(Student student) {
        return studentPersistencePort.createStudent(student);
    }

    @Override
    public Student findByEmail(String email) {
        return studentPersistencePort.findByEmail(email).orElse(null);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return studentPersistencePort
                .findByEmail(email)
                .map(StudentUserDetails::new)
                .orElse(null);
    }
}
