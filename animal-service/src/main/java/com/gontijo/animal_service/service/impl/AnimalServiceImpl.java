package com.gontijo.animal_service.service.impl;

import com.gontijo.animal_service.model.Animal;
import com.gontijo.animal_service.repository.AnimalRepository;
import com.gontijo.animal_service.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return animalRepository.findById(id);
    }

    @Override
    public Animal save(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public void deleteById(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public Animal update(Long id, Animal animalAtualizado) {
        animalAtualizado.setId(id);
        return animalRepository.save(animalAtualizado);
    }

    @Override
    public List<Animal> findByOngId(Long id) {
        return animalRepository.findByOngId(id);
    }

    @Override
    public List<Animal> filterByColor(String cor) {
        List<Animal> all = findAll();
        List<Animal> filtered = all.stream().filter(animal -> animal.getCor().toLowerCase().startsWith(cor.toLowerCase())).toList();
        return filtered;
    }
}
