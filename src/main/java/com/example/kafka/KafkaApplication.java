package com.example.kafka;

import com.example.kafka.repository.CurrencyMongoRepository;
import com.example.kafka.scheduled.CentralBankSurvey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Objects;


@SpringBootApplication
@EnableMongoRepositories
@EnableCaching
public class KafkaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(KafkaApplication.class, args);
        context.getBean(CurrencyMongoRepository.class).deleteAll();
        var ch = context.getBean(RedisCacheManager.class);
        ch.getCacheNames().forEach(obj-> Objects.requireNonNull(ch.getCache(obj)).clear());
        var survey = context.getBean(CentralBankSurvey.class);
        survey.interview();
    }

}
