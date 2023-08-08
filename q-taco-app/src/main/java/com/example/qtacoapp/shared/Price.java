package com.example.qtacoapp.shared;

import java.math.BigDecimal;
import java.util.Currency;

public record Price(BigDecimal amount, Currency currency) {
}