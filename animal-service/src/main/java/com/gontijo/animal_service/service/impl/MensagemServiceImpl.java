package com.gontijo.animal_service.service.impl;

import com.gontijo.animal_service.model.Mensagem;
import com.gontijo.animal_service.service.MensagemService;
import com.gontijo.animal_service.service.feign.MensagemClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemServiceImpl implements MensagemService {

    private final MensagemClient mensagemClient;

    @Override
    public List<Mensagem> getById(Long id) {
        return mensagemClient.getById(id);
    }
}
//        RestClient restClient = RestClient.create();
//        var serverUrl = String.format("http://localhost:8083/animal/%d", id);
//        ResponseEntity<List<Mensagem>> response = restClient.get()
//                .uri(serverUrl)
//                .retrieve()
//                .toEntity(new ParameterizedTypeReference<List<Mensagem>>() {});
//
//        return response.getBody();
//    }
