package com.stewie.identity_service.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @Size(min=8,message = "INVALID_PASSWORD")
    private  String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
