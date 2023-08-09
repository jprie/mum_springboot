package com.example.qtacoapp.domain.customer;

import jakarta.validation.constraints.Email;

public record EmailAddress(
        @Email
        String address
) {
}
