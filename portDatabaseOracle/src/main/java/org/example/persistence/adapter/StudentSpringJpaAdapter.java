package org.example.persistence.adapter;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.student.StudentPersistencePort;
import org.example.domain.student.models.Student;
import org.example.domain.student.models.StudentRole;
import org.example.persistence.entity.StudentEntity;
import org.example.persistence.repository.StudentRepository;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class StudentSpringJpaAdapter implements StudentPersistencePort {
    final StudentRepository studentRepository;

    public StudentSpringJpaAdapter(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudent() {
        List<StudentEntity> x = studentRepository.findAll();
        return studentRepository
                .findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Student createStudent(Student student) {
        StudentEntity entity = toEntity(student);
        StudentEntity saved = studentRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
       return  studentRepository
               .findByEmail(email)
               .map(this::toDomain);
    }

    private Student toDomain(StudentEntity entity) {
        return Student
                .builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .roles(entity.getRoles()
                        .stream()
                        .map(role -> StudentRole.valueOf(role.getName().name()))
                        .collect(Collectors.toList()))
                .build();
    }

    private StudentEntity toEntity(Student student) {
        return StudentEntity.builder()
                .email(student.getEmail())
                .mobileNumber(student.getMobileNumber())
                .build();
    }

}
