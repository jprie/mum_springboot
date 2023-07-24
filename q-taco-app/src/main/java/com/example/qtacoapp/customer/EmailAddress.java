package com.example.qtacoapp.customer;

import jakarta.validation.constraints.Email;

public record EmailAddress(
        @Email
        String address
) {
}
