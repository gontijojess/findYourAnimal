package com.gontijo.notificacao_service.service.impl;
import com.gontijo.notificacao_service.model.Mensagem;
import com.gontijo.notificacao_service.service.MensagemService;
import com.gontijo.notificacao_service.service.feign.MensagemClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MensagemServiceImpl implements MensagemService {

    private final MensagemClient mensagemClient;

    @Override
    public Optional<Mensagem> getById(Long id) {
        return mensagemClient.getById(id);
    }

}
