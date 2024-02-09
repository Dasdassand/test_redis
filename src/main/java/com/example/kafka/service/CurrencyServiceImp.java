package com.example.kafka.service;

import com.example.kafka.entity.Currency;
import com.example.kafka.repository.CurrencyMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "cur")
public class CurrencyServiceImp implements CurrencyService {
    private final CurrencyMongoRepository repository;
    private final RedisCacheManager manager;

    @Override
    public void addOrUpdate(List<Currency> currency) {
        if (currency.equals(getCurrencies())) {
            return;
        }
        currency.forEach(this::addToCache);
        repository.saveAll(currency);
    }


    private void addToCache(Currency currency) {
        Objects.requireNonNull(manager.getCache("cur")).put(currency.getId(), currency);
    }

    @Cacheable(key = "#a0")
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
