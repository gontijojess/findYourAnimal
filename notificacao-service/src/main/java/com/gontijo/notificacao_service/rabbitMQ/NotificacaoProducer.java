package com.gontijo.notificacao_service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gontijo.notificacao_service.model.Mensagem;
import com.gontijo.notificacao_service.model.Notificacao;
import com.gontijo.notificacao_service.service.NotificacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacaoProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final NotificacaoService notificacaoService;

    public void sendNotificao(Mensagem mensagemRecebida) throws JsonProcessingException {
        Notificacao notificacao = notificacaoService.save(mensagemRecebida);
        String notificacaoJson = objectMapper.writeValueAsString(notificacao);

        amqpTemplate.convertAndSend("notificacao-enviada-exc", "notificacao-enviada-rk", notificacaoJson);
    }

    public void sendNotificacaoEnviada(Mensagem mensagem) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        String mensagemJson = objectMapper.writeValueAsString(mensagem);
        amqpTemplate.convertAndSend("notificacao-enviada-sucesso-exc", "notificacao-enviada-sucesso-rk", mensagemJson);
    }

    public void sendNotificacaoErro(Mensagem mensagem) throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        String mensagemJson = objectMapper.writeValueAsString(mensagem);
        amqpTemplate.convertAndSend("notificacao-erro-exc", "notificacao-erro-rk", mensagemJson);
    }
}
