package com.example.kafka;

import com.example.kafka.repository.CurrencyMongoRepository;
import com.example.kafka.scheduled.CentralBankSurvey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
@EnableCaching
public class KafkaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(KafkaApplication.class, args);
        context.getBean(CurrencyMongoRepository.class).deleteAll();
        var survey = context.getBean(CentralBankSurvey.class);
        survey.interview();
    }

}
