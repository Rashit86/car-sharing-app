package com.pet.project.carsharingapp.controller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pet.project.carsharingapp.request.CarRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api(value = "Car sharing service")
@RestController
public interface CarSharingService {

    @ApiOperation(value = "Reserve car or cancel reservation")
    @PostMapping(value = "/rent")
    void rent(@Valid @NotNull CarRequest request) throws JsonProcessingException;

}
