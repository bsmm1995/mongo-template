package com.pichincha.mongotemplate.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface Repository<T, ID> {
    Optional<T> findById(ID id);

    List<T> findAll();

    T save(T entity);

    List<T> saveAll(Iterable<T> entities);

    ID delete(T entity);
}
