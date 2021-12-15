package com.pet.project.carsharingapp.service.api;

import com.pet.project.carsharingapp.enums.CarModelEnum;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;

@Controller
public interface KafkaService {

    /**
     * Reservation the car
     * @param userName name of user
     * @param carModel model of car
     * */
    void reserve(@NotNull String userName, @NotNull CarModelEnum carModel);

    /**
     * Cancel the car reservation
     * @param userName name of user
     * @param carModel model of car
     * */
    void cancel(@NotNull String userName, @NotNull CarModelEnum carModel);
}
