package com.pichincha.mongotemplate.repository.impl;

import com.pichincha.mongotemplate.domain.CustomerEntity;
import com.pichincha.mongotemplate.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<CustomerEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, CustomerEntity.class));
    }

    @Override
    public List<CustomerEntity> findAll() {
        return mongoTemplate.findAll(CustomerEntity.class);
    }

    @Override
    public CustomerEntity save(CustomerEntity data) {
        return mongoTemplate.insert(data);
    }

    @Override
    public List<CustomerEntity> saveAll(Iterable<CustomerEntity> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        return Streamable.of(entities).stream().map(this::save).toList();
    }

    @Override
    public String delete(CustomerEntity entity) {
        mongoTemplate.remove(entity);
        return entity.getId();
    }
}
