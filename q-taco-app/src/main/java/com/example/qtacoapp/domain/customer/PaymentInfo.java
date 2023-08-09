package com.example.qtacoapp.domain.customer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;

public record PaymentInfo(
        @CreditCardNumber
        String ccNumber,
        String ccHolder,
        @Min(100)
        @Max(999)
        int ccCCV,
        @Pattern(regexp = "\\d{2}/\\d{2}")
        String ccExpires
) {
}
