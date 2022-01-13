package com.pet.project.carsharingapp.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pet.project.carsharingapp.enums.CarModelEnum;
import com.pet.project.carsharingapp.enums.RequiredActionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class CarRequest implements Serializable {

    private String userName;
    private CarModelEnum carModel;
    private RequiredActionEnum requiredAction;
    private String time;

}
