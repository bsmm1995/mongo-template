package com.pichincha.mongotemplate.repository.impl;

import com.pichincha.mongotemplate.domain.PetEntity;
import com.pichincha.mongotemplate.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PetRepositoryImpl implements PetRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<PetEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, PetEntity.class));
    }

    @Override
    public List<PetEntity> findAll() {
        return mongoTemplate.findAll(PetEntity.class);
    }

    @Override
    public PetEntity save(PetEntity data) {
        return mongoTemplate.insert(data);
    }

    @Override
    public List<PetEntity> saveAll(Iterable<PetEntity> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        return Streamable.of(entities).stream().map(this::save).toList();
    }

    @Override
    public String delete(PetEntity entity) {
        mongoTemplate.remove(entity);
        return entity.getId();
    }
}
