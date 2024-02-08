package com.example.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Currency{
    @Id
    private String id;
    private int numCode;
    private String charCode;
    private int nominal;
    private String name;
    private double value;
    private double vunitRate;
    private LocalDate date;
}
