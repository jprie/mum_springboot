package com.example.qtacoapp.customer;

import jakarta.validation.constraints.Pattern;

public record PhoneNumber(
        @Pattern(regexp =
                "^\\+\\d{2,3}\\s\\d{3}\\s\\d{7}")
        String number
) {
}
