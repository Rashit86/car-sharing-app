package com.pet.project.carsharingapp.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pet.project.carsharingapp.controller.api.CarSharingService;
import com.pet.project.carsharingapp.request.CarRequest;
import com.pet.project.carsharingapp.service.api.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarSharingServiceImpl implements CarSharingService {

    private final KafkaService kafkaService;

    @Override
    public void rent(CarRequest request) throws JsonProcessingException {
        kafkaService.rent(request);
    }
}
