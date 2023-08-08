package com.example.qtacoapp.payment;

import lombok.Data;

@Data
public class PaymentForm {
    private String ccNumber;
    private String ccHolder;
    private String ccExpireDate;
    private String ccCCV;
}
