package com.gontijo.ong_service.service.impl;
import com.gontijo.ong_service.model.Animal;
import com.gontijo.ong_service.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService {
    @Override
    public List<Animal> getAnimalByOngId(Long id) {
        RestClient restClient = RestClient.create();
        var serverUrl = String.format("http://localhost:8081/ong/%d", id);
        ResponseEntity<List<Animal>> response = restClient.get()
                .uri(serverUrl)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Animal>>() {});

        return response.getBody();
    }
}