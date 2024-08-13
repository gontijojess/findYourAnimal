package com.gontijo.animal_service.service.impl;

import com.gontijo.animal_service.model.Mensagem;
import com.gontijo.animal_service.service.MensagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemServiceImpl implements MensagemService {
    @Override
    public List<Mensagem> getMessageByPetId(Long id) {
        RestClient restClient = RestClient.create();
        var serverUrl = String.format("http://localhost:8083/animal/%d", id);
        ResponseEntity<List<Mensagem>> response = restClient.get()
                .uri(serverUrl)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<Mensagem>>() {});

        return response.getBody();
    }
}
