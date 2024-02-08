package com.example.kafka.service;

import com.example.kafka.entity.Currency;
import com.example.kafka.repository.CurrencyMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@CacheConfig(cacheNames = "cur")
@RequiredArgsConstructor
public class CurrencyServiceImp implements CurrencyService {
    private final CurrencyMongoRepository repository;

    @Override
    public void addOrUpdate(List<Currency> currency) {
        if (currency.equals(getCurrencies())) {
            return;
        }
        currency.forEach(this::add);
        repository.saveAll(currency);
    }

    @Deprecated
    @CachePut(key = "#currency.id")
    public Currency add(Currency currency) {
        return currency;
    }

    @Cacheable(key = "#id")
    @Override
    public Optional<Currency> getCurrency(String id) {
        return repository.findById(id);
    }

    @Cacheable
    @Override
    public List<Currency> getCurrencies() {
        return repository.findAll();
    }
}
