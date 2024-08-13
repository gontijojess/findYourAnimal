package com.gontijo.mensagem_service.service.impl;

import com.gontijo.mensagem_service.model.Mensagem;
import com.gontijo.mensagem_service.repository.MensagemRepository;
import com.gontijo.mensagem_service.service.MensagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MensagemServiceImpl implements MensagemService {
    private final MensagemRepository mensagemRepository;

    @Override
    public Mensagem save(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    @Override
    public List<Mensagem> findByAnimalId(Long id) {
        return mensagemRepository.findMensagemByAnimalId(id);
    }

    @Override
    public void deleteById(Long id) {
        mensagemRepository.deleteById(id);
    }


}
