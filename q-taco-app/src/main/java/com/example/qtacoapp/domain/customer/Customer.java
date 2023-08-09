package com.example.qtacoapp.domain.customer;

import com.example.qtacoapp.domain.payment.PaymentProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;

    @Embedded
    private Address address;
    @Embedded
    private EmailAddress email;
    @Embedded
    private PhoneNumber phone;
    @Embedded
    private PaymentProvider paymentProvider;


    public String fullName() {
        return firstName + " " + lastName;
    }

}
