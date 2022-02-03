package com.pet.project.carsharingapp.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pet.project.carsharingapp.request.CarRequest;
import org.springframework.stereotype.Controller;

@Controller
public interface KafkaService {

    void rent(CarRequest request) throws JsonProcessingException;
}
