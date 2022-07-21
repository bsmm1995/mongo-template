package com.pichincha.mongotemplate.service;

import com.pichincha.mongotemplate.domain.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerEntity getById(String id);

    List<CustomerEntity> getAll();

    CustomerEntity save(CustomerEntity data);

    CustomerEntity update(String id, CustomerEntity data);

    String deleteById(String id);
}
