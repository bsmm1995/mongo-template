package com.pichincha.mongotemplate.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PetDto implements Serializable {
    private String id;
    private String customerId;
    private String name;
    private String gender;
}
