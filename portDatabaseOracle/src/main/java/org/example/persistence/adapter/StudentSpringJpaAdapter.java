package org.example.persistence.adapter;

import org.example.domain.student.StudentPersistencePort;
import org.example.domain.student.models.Student;
import org.example.persistence.entity.StudentEntity;
import org.example.persistence.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StudentSpringJpaAdapter  implements StudentPersistencePort {
    final StudentRepository studentRepository;

    public StudentSpringJpaAdapter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        List<StudentEntity> _t = studentRepository.findAll();
        System.out.println(" ..... size query = " +  _t.size());
        return studentRepository.findAll().stream().map(studentEntity -> Student
                .builder()
                .id(studentEntity.getId())
                .email(studentEntity.getEmail())
                .build()).collect(Collectors.toList());
    }
}
