package com.example.kafka.controller;

import com.example.kafka.dto.GetCurrency;
import com.example.kafka.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService service;
    private final RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.PATCH, path = "/update")
    public void update() {
        String result = restTemplate.getForObject("https://www.cbr.ru/scripts/XML_daily.asp?date_req=02/03/2002", String.class);
        service.addCurrency(result);
    }

    @GetMapping("/get")
    public GetCurrency getCurrency() {
        return service.getCurrency();
    }
}
