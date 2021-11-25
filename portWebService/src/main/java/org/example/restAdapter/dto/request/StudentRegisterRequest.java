package org.example.restAdapter.dto.request;

import lombok.Data;

@Data
public class StudentRegisterRequest {
    private String fullName;
    private String email;
    private String gender;
}
