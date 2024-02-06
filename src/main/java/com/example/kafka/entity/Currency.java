package com.example.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Currency")
public class Currency implements Serializable{
    private String id;
    private int numCode;
    private String charCode;
    private int nominal;
    private String name;
    private double value;
    private double valueRate;
}
