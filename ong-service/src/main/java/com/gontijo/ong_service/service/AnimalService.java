package com.gontijo.ong_service.service;

import com.gontijo.ong_service.model.Animal;

import java.util.List;

public interface AnimalService {
    List<Animal> getAnimalByOngId(Long id);
}
