package com.pet.project.carsharingapp.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pet.project.carsharingapp.controller.api.CarSharingService;
import com.pet.project.carsharingapp.request.CarRequest;
import com.pet.project.carsharingapp.service.api.RentCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarSharingServiceImpl implements CarSharingService {

    private final RentCarService rentCarService;

    @Override
    public void rent(CarRequest request) throws JsonProcessingException {
        rentCarService.rent(request);
    }
}
