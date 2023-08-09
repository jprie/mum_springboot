package com.example.qtacoapp.web.register;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatch(message = "passwords do not match")
public class UserForm {
        @Size(min = 4, message = "username must at least be 4 characters")
        private String username;
        @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
        message = "must contain ...")
                private String password;
        String confirmPassword;
}
