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

    }


    @CachePut(value = "cur", key = "#currency.id")
    public Currency add(Currency currency) {
        System.out.println("id:" + currency.getId());
        return repository.save(currency);
    }

    @Cacheable(value = "cur",key = "#id")
    @Override
    public Optional<Currency> getCurrency(String id) {
        return repository.findById(id);
    }

    @Cacheable(value = "cur")
    @Override
    public List<Currency> getCurrencies() {
        return repository.findAll();
    }
}
