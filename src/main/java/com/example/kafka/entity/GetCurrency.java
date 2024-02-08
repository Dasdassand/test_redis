package com.example.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document
@Getter
@ToString
@AllArgsConstructor
public class GetCurrency {
    @Id
    private LocalDate date;
    private List<Currency> currencies;
}
