package com.example.kafka.parser;

import com.example.kafka.entity.Currency;

import java.util.List;

public interface CurrencyParser{
    List<Currency> parse(String xml);
}
