package com.pichincha.mongotemplate.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@Document(collection = "customers")
public class CustomerEntity implements Serializable {
    @Id
    private String id;
    private String name;
    private String lastName;
    private ArrayList<PetEntity> pets;
}
