package com.pichincha.mongotemplate.service.impl;

import com.pichincha.mongotemplate.domain.PetEntity;
import com.pichincha.mongotemplate.repository.PetRepository;
import com.pichincha.mongotemplate.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    @Override
    public PetEntity getById(String id) {
        return getEntity(id);
    }

    @Override
    public List<PetEntity> getAll() {
        return this.petRepository.findAll();
    }

    @Override
    public PetEntity save(PetEntity data) {
        return this.petRepository.save(data);
    }

    @Override
    public String deleteById(String id) {
        return this.petRepository.delete(getEntity(id));
    }

    private PetEntity getEntity(String id) {
        return this.petRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Record with id %s not found.", id)));
    }
}
