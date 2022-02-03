package com.pet.project.carsharingapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.project.carsharingapp.enums.CarModelEnum;
import com.pet.project.carsharingapp.enums.RequiredActionEnum;
import com.pet.project.carsharingapp.request.CarRequest;
import com.pet.project.carsharingapp.service.api.KafkaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${car-sharing-app.topic-name.car-request}")
    private String carReqTopicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void rent(CarRequest request) throws JsonProcessingException {
        request.setTime(LocalDateTime.now(Clock.systemUTC()));
        kafkaTemplate.send(carReqTopicName, request.getCarModel().toString(), objectMapper.writeValueAsString(request));
    }
}
