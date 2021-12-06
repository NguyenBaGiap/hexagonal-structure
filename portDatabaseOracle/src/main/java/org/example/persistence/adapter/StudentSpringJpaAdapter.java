package org.example.persistence.adapter;

import lombok.extern.slf4j.Slf4j;
import org.example.domain.student.StudentPersistencePort;
import org.example.domain.student.models.Student;
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
        StudentEntity entity =  new StudentEntity();
        BeanUtils.copyProperties(student, entity);
        StudentEntity saved = studentRepository.save(entity);
        BeanUtils.copyProperties(saved, student);
        return student;
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        StudentEntity entity = studentRepository.findByEmail(email);
        if(entity != null){
            Student student = new Student();
            BeanUtils.copyProperties(entity, student);
            return  Optional.of(student);
        }
        return Optional.empty();
    }

}
