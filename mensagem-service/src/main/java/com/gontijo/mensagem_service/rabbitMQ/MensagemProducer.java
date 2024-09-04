package com.gontijo.mensagem_service.rabbitMQ;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gontijo.mensagem_service.model.Mensagem;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MensagemProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public void send(Mensagem mensagem) throws JsonProcessingException {
        String mensagemJson = objectMapper.writeValueAsString(mensagem);
        amqpTemplate.convertAndSend("mensagem-enviada-exc", "mensagem-enviada-rk", mensagemJson);
    }
}
