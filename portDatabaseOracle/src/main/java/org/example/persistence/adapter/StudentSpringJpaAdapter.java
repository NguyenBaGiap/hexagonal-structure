package org.example.persistence.adapter;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.student.StudentPersistencePort;
import org.example.domain.student.models.Student;
import org.example.persistence.entity.StudentEntity;
import org.example.persistence.mapper.StudentMapper;
import org.example.persistence.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StudentSpringJpaAdapter implements StudentPersistencePort {
    final StudentRepository studentRepository;

    public StudentSpringJpaAdapter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository
                .findAll()
                .stream()
                .map(studentEntity -> Student
                        .builder()
                        .id(studentEntity.getId())
                        .email(studentEntity.getEmail())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public Student createStudent(Student student) {
        StudentEntity entity =  StudentMapper.INSTANCE.modelToEntity(student);
        StudentEntity saved = studentRepository.save(entity);
        return StudentMapper.INSTANCE.entityToModel(saved);
    }

}
