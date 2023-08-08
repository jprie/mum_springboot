package com.example.qtacoapp.tacoorder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderForm {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @NotBlank
    private String street;
    @NotBlank
    private String streetNr;
    @NotBlank
    private String city;
    @NotBlank
    private String state;

    @Max(9999)
    @Min(0000)
    private int zipCode;

    private String country;

    @Email
    private String email;
    @Pattern(regexp = "\\+\\d{2,3}")
    private String phonePrefix;
    @Pattern(regexp = "\\d{3}\\d{7,8}")
    private String phone;

}
