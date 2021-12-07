package org.example.restAdapter.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StudentRegisterResponse {
    private Long id;
    private String email;
    private String mobileNumber;
    private String accessToken;
    private List<String> roles;
}
