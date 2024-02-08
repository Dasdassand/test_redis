package com.example.kafka.scheduled;

import com.example.kafka.parser.CurrencyParser;
import com.example.kafka.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class CentralBankSurvey {
    private final CurrencyService service;
    private final RestTemplate restTemplate;
    private final CurrencyParser parser;

    @Scheduled(cron = "0 1 15 * * ?", zone = "Europe/Moscow")
    public void interview() {
        String xml = restTemplate.getForObject("https://www.cbr.ru/scripts/XML_daily.asp", String.class);
        var currencies = parser.parse(xml);
        service.addOrUpdate(currencies);
    }
}
