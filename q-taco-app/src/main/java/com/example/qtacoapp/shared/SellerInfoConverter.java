package com.example.qtacoapp.shared;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SellerInfoConverter implements Converter<SellerInfo, String> {
    @Override
    public String convert(SellerInfo source) {
        return source.id() + "+" + source.name();
    }
}
