package com.example.kafka.service;

import com.example.kafka.entity.Currency;
import com.example.kafka.entity.GetCurrency;

public interface CurrencyService {
    GetCurrency getCurrency();
    void addCurrency(String xml);

    Currency get(String id);
}
