package com.gontijo.ong_service.service.impl;

import com.gontijo.ong_service.model.Animal;
import com.gontijo.ong_service.service.AnimalService;
import com.gontijo.ong_service.service.feign.AnimalClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {

    private final AnimalClient animalClient;

    @Override
    public List<Animal> getAnimalsByOngId(Long id) {
        return animalClient.getAnimalsByOngId(id);
    }

    public Animal getAnimalById(Long id){
        return animalClient.getAnimalById(id);
    }


}
