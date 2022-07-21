package com.pichincha.mongotemplate.controller;

import com.pichincha.mongotemplate.domain.PetEntity;
import com.pichincha.mongotemplate.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{id}")
    PetEntity getById(@PathVariable String id) {
        return this.petService.getById(id);
    }

    @GetMapping
    List<PetEntity> getAll() {
        return this.petService.getAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    PetEntity create(@RequestBody PetEntity data) {
        return this.petService.save(data);
    }

    @DeleteMapping("/{id}")
    String deleteById(@PathVariable String id) {
        return this.petService.deleteById(id);
    }
}
