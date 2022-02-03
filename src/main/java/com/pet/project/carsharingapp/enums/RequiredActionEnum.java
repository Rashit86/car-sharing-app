package com.pet.project.carsharingapp.enums;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@ApiModel
@Getter
public enum RequiredActionEnum {
    RESERVE,
    CANCEL_RESERVATION
}
