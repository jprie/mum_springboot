package com.example.qtacoapp.shared;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PriceConverter implements Converter<Price, String> {
    @Override
    public String convert(Price source) {
        return source.currency() + "+" + source.amount().toString();
    }
}
