package com.example.ttacoapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {

    private String ccHolder;
    private String ccNumber;
    private String ccExpires;
    private String ccCCV;
}
