package com.pichincha.mongotemplate.domain.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class CustomerDto implements Serializable {
    @Id
    private String id;
    private String name;
    private String lastName;
    private ArrayList<PetDto> pets;
}
