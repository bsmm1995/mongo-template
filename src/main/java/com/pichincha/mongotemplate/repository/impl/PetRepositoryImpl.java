package com.pichincha.mongotemplate.repository.impl;

import com.mongodb.BasicDBObject;
import com.pichincha.mongotemplate.domain.PetEntity;
import com.pichincha.mongotemplate.repository.PetRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
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
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("customers")
                .localField("customerId")
                .foreignField("_id")
                .as("customer");

        Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(Criteria.where("_id").is(id)), lookupOperation);
        PetEntity result = mongoTemplate.aggregate(aggregation, "pets", PetEntity.class).getUniqueMappedResult();
        BasicDBObject BasicDBObject = mongoTemplate.aggregate(aggregation, "pets", BasicDBObject.class).getUniqueMappedResult();
        return Optional.ofNullable(result);
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
