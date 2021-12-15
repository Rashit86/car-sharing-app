package com.pet.project.carsharingapp.controller.impl;

import com.pet.project.carsharingapp.controller.api.CarSharingService;
import com.pet.project.carsharingapp.service.api.KafkaService;
import com.pet.project.carsharingapp.enums.CarModelEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarSharingServiceImpl implements CarSharingService {

    private final KafkaService kafkaService;

    @Override
    public void reserve(String userName, CarModelEnum carModel) {
        kafkaService.reserve(userName, carModel);
    }

    @Override
    public void cancel(String userName, CarModelEnum carModel) {
        kafkaService.cancel(userName, carModel);
    }
}
