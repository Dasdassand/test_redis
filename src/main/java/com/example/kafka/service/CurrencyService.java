package com.example.kafka.service;

import com.example.kafka.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    void addOrUpdate(List<Currency> currency);

    Optional<Currency> getCurrency(String id);

    List<Currency> getCurrencies();
}
