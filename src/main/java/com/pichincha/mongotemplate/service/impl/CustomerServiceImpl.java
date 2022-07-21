package com.pichincha.mongotemplate.service.impl;

import com.pichincha.mongotemplate.domain.CustomerEntity;
import com.pichincha.mongotemplate.domain.PetEntity;
import com.pichincha.mongotemplate.repository.CustomerRepository;
import com.pichincha.mongotemplate.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity getById(String id) {
        CustomerEntity entity = getEntity(id);
        orderPets(entity.getPets());
        return entity;
    }

    @Override
    public List<CustomerEntity> getAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public CustomerEntity save(CustomerEntity data) {
        return this.customerRepository.save(data);
    }

    @Override
    public CustomerEntity update(String id, CustomerEntity data) {
        CustomerEntity entity = getEntity(id);
        entity.setName(data.getName());
        entity.setLastName(data.getLastName());
        return this.customerRepository.save(entity);
    }

    @Override
    public String deleteById(String id) {
        return this.customerRepository.delete(getEntity(id));
    }

    private CustomerEntity getEntity(String id) {
        return this.customerRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Record with id %s not found.", id)));
    }

    private void orderPets(ArrayList<PetEntity> pets) {
        pets.sort(Comparator.comparing(PetEntity::getName));
    }
}
