package com.gontijo.notificacao_service.service;
import com.gontijo.notificacao_service.model.Mensagem;
import com.gontijo.notificacao_service.model.Notificacao;
import java.util.List;

public interface NotificacaoService {
    Notificacao save(Mensagem mensagem);

    List<Notificacao> findAll();
}
