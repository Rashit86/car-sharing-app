package com.pet.project.carsharingapp.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pet.project.carsharingapp.request.CarRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public interface CarSharingService {

    @PostMapping(value = "/rent")
    void rent(@Valid CarRequest request) throws JsonProcessingException;

}
