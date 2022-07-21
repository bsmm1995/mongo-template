package com.pichincha.mongotemplate.repository.impl;

import com.pichincha.mongotemplate.domain.CustomerEntity;
import com.pichincha.mongotemplate.domain.PetEntity;
import com.pichincha.mongotemplate.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public Optional<CustomerEntity> findById(String id) {
        Aggregation aggregation = newAggregation(Aggregation.match(Criteria.where("_id").is(id)), getLookup(), sort(Sort.Direction.DESC, "pets.name"));
        return Optional.ofNullable(mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(CustomerEntity.class), CustomerEntity.class).getUniqueMappedResult());
    }

    @Override
    public List<CustomerEntity> findAll() {
        Aggregation aggregation = newAggregation(getLookup(), sort(Sort.Direction.DESC, "name"));
        return mongoTemplate.aggregate(aggregation, mongoTemplate.getCollectionName(CustomerEntity.class), CustomerEntity.class).getMappedResults();
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

    private LookupOperation getLookup() {
        return LookupOperation.newLookup().
                from(mongoTemplate.getCollectionName(PetEntity.class)).
                localField("_id").
                foreignField("customerId").
                as("pets");
    }
}
