package com.pet.project.carsharingapp;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class CarSharingAppApplication {

    @Value("${car-sharing-app.topic-name.car-request}")
    private String carRequestTopic;

    public static void main(String[] args) {
        SpringApplication.run(CarSharingAppApplication.class, args);
    }

    @Bean
    NewTopic createCarRequestTopic(){
        return TopicBuilder
                .name(carRequestTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
