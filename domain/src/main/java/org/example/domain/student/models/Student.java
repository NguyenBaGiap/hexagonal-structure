package org.example.domain.student.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Student {
    private Long id;
    private String fullName;
    private String email;
    private String gender;
    private StudentStatus status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
