package com.example.kafka.repository;

import com.example.kafka.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRedisRepository extends CrudRepository<Currency, String> {
}