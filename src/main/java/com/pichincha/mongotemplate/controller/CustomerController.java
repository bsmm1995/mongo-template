package com.pichincha.mongotemplate.controller;

import com.pichincha.mongotemplate.domain.CustomerEntity;
import com.pichincha.mongotemplate.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    CustomerEntity getById(@PathVariable String id) {
        return this.customerService.getById(id);
    }

    @GetMapping
    List<CustomerEntity> getAll() {
        return this.customerService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    CustomerEntity create(@RequestBody CustomerEntity data) {
        return this.customerService.save(data);
    }

    @PutMapping("/{id}")
    CustomerEntity update(@PathVariable String id, @RequestBody CustomerEntity data) {
        return this.customerService.update(id, data);
    }

    @DeleteMapping("/{id}")
    String deleteById(@PathVariable String id) {
        return this.customerService.deleteById(id);
    }
}
