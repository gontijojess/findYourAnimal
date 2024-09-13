package com.gontijo.notificacao_service.service;
import com.gontijo.notificacao_service.model.Mensagem;
import java.util.Optional;

public interface MensagemService {

    Optional<Mensagem> getById(Long id);
}
