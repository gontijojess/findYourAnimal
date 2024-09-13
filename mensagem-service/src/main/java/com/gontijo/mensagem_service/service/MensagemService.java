package com.gontijo.mensagem_service.service;

import com.gontijo.mensagem_service.model.Mensagem;

import java.util.List;
import java.util.Optional;

public interface MensagemService {
    Mensagem save(Mensagem mensagem);

    List<Mensagem> findByAnimalId(Long id);

    void deleteById(Long id);

    List<Mensagem> findAll();

    Optional<Mensagem> findById(Long id);

    Mensagem updateStatusEnviado(Long id, Mensagem mensagemAtualizada);

    Mensagem updateStatusErro(Long id, Mensagem mensagemAtualizada);
}
