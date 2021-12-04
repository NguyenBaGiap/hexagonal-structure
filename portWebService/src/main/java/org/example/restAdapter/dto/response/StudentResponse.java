package org.example.restAdapter.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {
    private Long id;
    private String email;
    private String mobileNumber;
}
