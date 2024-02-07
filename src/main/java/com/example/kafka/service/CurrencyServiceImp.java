package com.example.kafka.service;

import com.example.kafka.entity.GetCurrency;
import com.example.kafka.entity.Currency;
import com.example.kafka.parser.CurrencyParser;
import com.example.kafka.repository.CurrencyMongoRepository;
import com.example.kafka.repository.CurrencyRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImp implements CurrencyService {
    private final CurrencyParser parser;
    private final CurrencyRedisRepository repositoryR;
    private final CurrencyMongoRepository repositoryM;

    @Override
    public GetCurrency getCurrency() {
        var currency = repositoryR.findAll();
        Map<String, Currency> map = new HashMap<>();
        currency.forEach(obj -> map.put(obj.getCharCode(), obj));
        return new GetCurrency(LocalDate.now(), map);
    }

    @Override
    public void addCurrency(String xml) {
        var currencies = parser.parse(xml);
        repositoryR.saveAll(currencies.getCurrencyMap().values());
    }

    @Override
    public Currency get(String id) {
        repositoryM.saveAll(repositoryR.findAll());
        var M = repositoryM.findById(id);
        var R = repositoryR.findById(id);
        if (R.isEmpty())
            System.out.println("Rizdez");
        if (M.isEmpty())
            System.out.println("Mizdez");
        if (R.isPresent() && M.isPresent() && R.get().equals(M.get()))
            return R.get();
        return null;
    }
}
