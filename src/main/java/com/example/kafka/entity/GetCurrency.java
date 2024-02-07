package com.example.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Map;

@Document
@Getter
@AllArgsConstructor
public class GetCurrency {
    @Id
    private LocalDate date;
    private Map<String, Currency> currencyMap;
}
