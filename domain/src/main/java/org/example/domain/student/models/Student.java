package org.example.domain.student.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Long id;
    private String email;
    private String mobileNumber;
    private StudentStatus status;
    private List<StudentRole> roles;
}
