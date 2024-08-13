package com.gontijo.mensagem_service.controller;

import com.gontijo.mensagem_service.model.Mensagem;
import com.gontijo.mensagem_service.payload.MessagePayload;
import com.gontijo.mensagem_service.service.MensagemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MensagemController {
    private final MensagemService mensagemService;

    @PostMapping
    public ResponseEntity<MessagePayload> create (@RequestBody Mensagem mensagem){
        try {
            mensagemService.save(mensagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criada com sucesso!"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<?> getMessagesByAnimalId(@PathVariable Long id) {
        try {
            List<Mensagem> mensagens = mensagemService.findByAnimalId(id);
            if (mensagens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhuma mensagem encontrada"));
            }
            return ResponseEntity.ok(mensagens);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }

    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<MessagePayload> delete (@PathVariable Long id){
        try {
            mensagemService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Deletado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }
}
