package com.example.ttacoapp.domain;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    @NotEmpty
    private String ccHolder;
    @CreditCardNumber
    private String ccNumber;
//    @Pattern(regexp = "")
    @Size(min = 5, max = 5)
    private String ccExpires;
    @Size(min = 3, max = 3)
    private String ccCCV;
}
