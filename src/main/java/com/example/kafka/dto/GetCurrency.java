package com.example.kafka.dto;

import com.example.kafka.entity.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Getter
@AllArgsConstructor
public class GetCurrency {
    private LocalDate date;
    private Map<String, Currency> currencyMap;
}
