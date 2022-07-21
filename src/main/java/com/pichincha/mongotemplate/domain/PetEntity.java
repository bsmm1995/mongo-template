package com.pichincha.mongotemplate.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@Document(collection = "pets")
public class PetEntity implements Serializable {
    @Id
    private String id;
    private ObjectId customerId;
    @TextIndexed
    private String name;
    private String gender;
}
