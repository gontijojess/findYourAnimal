package com.gontijo.notificacao_service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gontijo.notificacao_service.model.Mensagem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificacaoConsumer {
    private final NotificacaoProducer notificacaoProducer;

    @RabbitListener(queues = {"mensagem-enviada-queue"})
    public void receive(@Payload String message) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Mensagem mensagem = objectMapper.readValue(message, Mensagem.class);
        try{
            Thread.sleep(8000);
            notificacaoProducer.sendNotificao(mensagem);
            notificacaoProducer.sendNotificacaoEnviada(mensagem);
        } catch(Exception e) {
            notificacaoProducer.sendNotificacaoErro(mensagem);
        }

    }
}
