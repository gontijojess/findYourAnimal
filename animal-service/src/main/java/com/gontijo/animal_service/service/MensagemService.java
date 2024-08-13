package com.gontijo.animal_service.service;

import com.gontijo.animal_service.model.Mensagem;

import java.util.List;

public interface MensagemService {

    List<Mensagem> getMessageByPetId(Long id);
}
