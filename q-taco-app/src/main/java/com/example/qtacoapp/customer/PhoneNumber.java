package com.example.qtacoapp.customer;

import jakarta.validation.constraints.Pattern;

public record PhoneNumber(
        String prefix,
        @Pattern(regexp =
                "^\\d{3}\\d{7,8}$")
        String number
) {
}
