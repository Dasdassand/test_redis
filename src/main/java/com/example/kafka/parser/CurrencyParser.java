package com.example.kafka.parser;

import com.example.kafka.dto.GetCurrency;

public interface CurrencyParser{
    GetCurrency parse(String xml);
}
