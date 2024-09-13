package com.gontijo.ong_service.service;

import com.gontijo.ong_service.model.Animal;

import java.util.List;

public interface AnimalService {
    List<Animal> getAnimalsByOngId(Long id);
    Animal getAnimalById(Long id);
}
