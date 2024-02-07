package com.example.kafka.parser;

import com.example.kafka.entity.GetCurrency;

public interface CurrencyParser{
    GetCurrency parse(String xml);
}
