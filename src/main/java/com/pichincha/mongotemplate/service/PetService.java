package com.pichincha.mongotemplate.service;

import com.pichincha.mongotemplate.domain.PetEntity;

import java.util.List;

public interface PetService {
  PetEntity getById(String id);

  List<PetEntity> getAll();

  PetEntity save(PetEntity data);

  String deleteById(String id);
}
