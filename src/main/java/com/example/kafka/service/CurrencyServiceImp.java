package com.example.kafka.service;

import com.example.kafka.dto.GetCurrency;
import com.example.kafka.entity.Currency;
import com.example.kafka.parser.CurrencyParser;
import com.example.kafka.repository.RedisCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImp implements CurrencyService {
    private final CurrencyParser parser;
    private final RedisCRUDRepository repository;

    @Override
    public GetCurrency getCurrency() {
        var currency = repository.findAll();
        Map<String, Currency> map = new HashMap<>();
        currency.forEach(obj->map.put(obj.getCharCode(),obj));
        return new GetCurrency(LocalDate.now(), map);
    }

    @Override
    public void addCurrency(String xml) {
        var currencies = parser.parse(xml);
        currencies.getCurrencyMap().forEach((key, value) -> repository.save(value));
    }
}
