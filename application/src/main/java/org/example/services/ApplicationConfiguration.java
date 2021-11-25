package org.example.services;

import org.example.domain.student.StudentPersistencePort;
import org.example.services.adapter.StudentServiceAdapter;
import org.example.services.ports.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public StudentService getStudentService(StudentPersistencePort studentPersistencePort){
        return new StudentServiceAdapter(studentPersistencePort);
    }
}
