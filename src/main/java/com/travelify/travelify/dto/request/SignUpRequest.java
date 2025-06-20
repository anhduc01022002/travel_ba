package com.travelify.travelify.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpRequest {
    String fullName;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@(?i)gmail\\.com$", message = "EMAIL_VALIDATE")
    String email;

    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
}
