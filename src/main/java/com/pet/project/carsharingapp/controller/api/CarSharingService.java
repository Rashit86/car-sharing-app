package com.pet.project.carsharingapp.controller.api;

import com.pet.project.carsharingapp.enums.CarModelEnum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public interface CarSharingService {

    @PostMapping(value = "/reserve")
    void reserve(@NotNull String userName, @NotNull CarModelEnum carModel);

    @PostMapping(value = "/cancel")
    void cancel(@NotNull String userName, @NotNull CarModelEnum carModel);
}
