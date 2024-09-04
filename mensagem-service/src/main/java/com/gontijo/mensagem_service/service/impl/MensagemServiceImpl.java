package com.gontijo.mensagem_service.service.impl;

import com.gontijo.mensagem_service.model.Mensagem;
import com.gontijo.mensagem_service.model.enums.StatusMensagem;
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

    @Override
    public List<Mensagem> findAll() {
        return mensagemRepository.findAll();
    }

    @Override
    public Mensagem updateStatusEnviado(Long id, Mensagem mensagemAtualizada) {
        mensagemAtualizada.setId(id);
        mensagemAtualizada.setStatus(StatusMensagem.ENVIADA);
        return mensagemRepository.save(mensagemAtualizada);
    }

    @Override
    public Mensagem updateStatusErro(Long id, Mensagem mensagemAtualizada) {
        mensagemAtualizada.setId(id);
        mensagemAtualizada.setStatus(StatusMensagem.ERRO);
        return mensagemRepository.save(mensagemAtualizada);
    }
}
