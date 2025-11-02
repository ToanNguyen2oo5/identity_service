package com.stewie.identity_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class UserCreationRequest {
    @Size(min=3,message = "INVALID_USERNAME")
    @NotBlank(message = "INVALID_USERNAME" )
    @Pattern(
            regexp = "^[a-zA-Z0-9]+$",
            message = "INVALID_USERNAME"
    )
    private String username;
    @Size(min=8,message = "INVALID_PASSWORD")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;


}
