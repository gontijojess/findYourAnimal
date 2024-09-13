package com.gontijo.notificacao_service.service.impl;

import com.gontijo.notificacao_service.model.Ong;
import com.gontijo.notificacao_service.service.OngService;
import com.gontijo.notificacao_service.service.feign.OngClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OngServiceImpl implements OngService {
    private final OngClient ongClient;

    @Override
    public Ong getByAnimalId(Long id) {
        ResponseEntity<Ong> response = ongClient.getByAnimalId(id);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        } else {
            throw new EntityNotFoundException("ONG não encontrada para o ID do animal: " + id);
        }
    }
}
