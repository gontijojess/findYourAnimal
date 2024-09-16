package com.gontijo.mensagem_service.controller;

import com.gontijo.mensagem_service.model.Mensagem;
import com.gontijo.mensagem_service.model.enums.StatusMensagem;
import com.gontijo.mensagem_service.payload.MessagePayload;
import com.gontijo.mensagem_service.rabbitMQ.MensagemProducer;
import com.gontijo.mensagem_service.service.MensagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class MensagemController {
    private final MensagemService mensagemService;
    private final MensagemProducer mensagemProducer;

    @Operation(description = "Cria uma nova mensagem com as informações do RequestBody")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mensagem criada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping
    public ResponseEntity<MessagePayload> create (@RequestBody Mensagem mensagem){
        try {
            LocalDateTime momentoAtual = LocalDateTime.now();
            mensagem.setDataCriacao(momentoAtual);
            mensagem.setStatus(StatusMensagem.CRIADA);
            Mensagem savedMensagem = mensagemService.save(mensagem);
            mensagemProducer.send(savedMensagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criada com sucesso!"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(description = "Retorna uma mensagem com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Mensagem não encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @GetMapping({"/{id}"})
    public ResponseEntity<?> findById (@PathVariable Long id){
        try {
            Optional<Mensagem> localizado = mensagemService.findById(id);
            if(localizado.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhum resultado encontrado"));
            } else {
                return ResponseEntity.ok(localizado);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(description = "Retorna lista de mensagens com base no ID do animal fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagens encontradas.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Nenhuma mensagem encontrada para esse animal.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
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

    @Operation(description = "Deleta mensagem por meio do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem deletada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
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
