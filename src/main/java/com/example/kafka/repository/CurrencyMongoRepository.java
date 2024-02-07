package com.example.kafka.repository;

import com.example.kafka.entity.Currency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyMongoRepository extends MongoRepository<Currency, String> {
}
