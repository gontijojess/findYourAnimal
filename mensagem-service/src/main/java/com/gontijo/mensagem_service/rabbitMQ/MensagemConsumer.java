package com.gontijo.mensagem_service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gontijo.mensagem_service.model.Mensagem;
import com.gontijo.mensagem_service.model.enums.StatusMensagem;
import com.gontijo.mensagem_service.service.MensagemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MensagemConsumer {
    private final MensagemService mensagemService;

    @RabbitListener(queues = {"notificacao-enviada-sucesso-queue"})
    public void receiveSucesso(@Payload String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Mensagem mensagem = objectMapper.readValue(message, Mensagem.class);
        mensagemService.updateStatusEnviado(mensagem.getId(), mensagem);
    }

    @RabbitListener(queues = {"notificacao-erro-queue"})
    public void receiveErro(@Payload String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Mensagem mensagem = objectMapper.readValue(message, Mensagem.class);
        mensagemService.updateStatusErro(mensagem.getId(), mensagem);
    }
}

