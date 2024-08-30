package com.gontijo.mensagem_service.service;

import com.gontijo.mensagem_service.model.Mensagem;

import java.util.List;

public interface MensagemService {
    Mensagem save(Mensagem mensagem);

    List<Mensagem> findByAnimalId(Long id);

    void deleteById(Long id);

    List<Mensagem> findAll();
}
