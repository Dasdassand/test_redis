package com.example.kafka.service;

import com.example.kafka.dto.GetCurrency;

public interface CurrencyService {
    GetCurrency getCurrency();
    void addCurrency(String xml);
}
