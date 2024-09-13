package com.gontijo.notificacao_service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gontijo.notificacao_service.model.Mensagem;
import com.gontijo.notificacao_service.model.Notificacao;
import com.gontijo.notificacao_service.model.Ong;
import com.gontijo.notificacao_service.repository.NotificaoRepository;
import com.gontijo.notificacao_service.service.NotificacaoService;
import com.gontijo.notificacao_service.service.OngService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacaoServiceImpl implements NotificacaoService {
    private final NotificaoRepository notificaoRepository;
    private final OngService ongService;

    @Override
    public Notificacao save(Mensagem mensagem) {
        Long mensagemId = mensagem.getId();
        long animalId = mensagem.getAnimalId();
        Ong ong = ongService.getByAnimalId(animalId);
        Notificacao notificacao = new Notificacao();
        notificacao.setOngEmail(ong.getEmail());
        notificacao.setOngId(ong.getId());
        notificacao.setOngName(ong.getNomeOng());
        notificacao.setMensagemId(mensagemId);
        return notificaoRepository.save(notificacao);
    }

    @Override
    public List<Notificacao> findAll() {
        return notificaoRepository.findAll();
    }


}
