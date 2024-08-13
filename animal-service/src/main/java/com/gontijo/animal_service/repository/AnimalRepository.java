package com.gontijo.animal_service.repository;

import com.gontijo.animal_service.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByOngId(Long id);
}
