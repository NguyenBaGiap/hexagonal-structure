package org.example.persistence;

import org.example.domain.student.StudentPersistencePort;
import org.example.persistence.adapter.StudentSpringJpaAdapter;
import org.example.persistence.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDataJpaAdapterConfiguration {
    @Bean
    public StudentPersistencePort getStudentPersistencePort(StudentRepository studentRepository) {
        return new StudentSpringJpaAdapter(studentRepository);
    }
}
