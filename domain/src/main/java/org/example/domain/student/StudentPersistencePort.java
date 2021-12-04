package org.example.domain.student;

import org.example.domain.student.models.Student;
import java.util.List;

public interface StudentPersistencePort {
    List<Student> getAllStudent();
    Student createStudent(Student student);
}
