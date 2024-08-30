package com.gontijo.animal_service.service;

import com.gontijo.animal_service.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {
    List<Animal> findAll();

    Optional<Animal> findById(Long id);

    Animal save(Animal animal);

    void deleteById(Long id);

    Animal update(Long id, Animal animalAtualizado);

    List<Animal> findByOngId(Long id);

    List<Animal> filterByColor(String cor);

}
