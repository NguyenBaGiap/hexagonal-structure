package org.example.restAdapter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class StudentRegisterDTO {

    @NotNull
    private String email;

    @NotNull
    private String mobileNumber;
}
