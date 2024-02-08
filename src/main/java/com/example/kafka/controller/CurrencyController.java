package com.example.kafka.controller;

import com.example.kafka.entity.Currency;
import com.example.kafka.parser.CurrencyParser;
import com.example.kafka.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService service;
    private final CurrencyParser parser;

    @GetMapping("/get")
    public List<Currency> getCurrency() {
        return service.getCurrencies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurrencyById(@PathVariable String id) {
        var optionalCurrency = service.getCurrency(id);
        return optionalCurrency
                .map(currency -> new ResponseEntity<>(currency, HttpStatusCode.valueOf(200)))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatusCode.valueOf(404)));
    }

    @PostMapping("")
    public List<Currency> addCurrency(@RequestBody String xml) {
        service.addOrUpdate(parser.parse(xml));
        return parser.parse(xml);
    }
}
