package com.pet.project.carsharingapp.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pet.project.carsharingapp.enums.CarModelEnum;
import com.pet.project.carsharingapp.enums.RequiredActionEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@ApiModel
public class CarRequest implements Serializable {

    @NotNull
    @ApiModelProperty
    private String userName;

    @NotNull
    @ApiModelProperty
    private CarModelEnum carModel;

    @NotNull
    @ApiModelProperty
    private RequiredActionEnum requiredAction;

    @JsonSerialize(using = com.pet.project.carsharingapp.util.json.LocalDateTimeSerializer.class)
    @ApiModelProperty(hidden = true)
    private LocalDateTime time;

}
