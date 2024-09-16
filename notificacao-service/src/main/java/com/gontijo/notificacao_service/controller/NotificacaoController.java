package com.gontijo.notificacao_service.controller;
import com.gontijo.notificacao_service.model.Notificacao;
import com.gontijo.notificacao_service.payload.MessagePayload;
import com.gontijo.notificacao_service.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificacaoController {
    private final NotificacaoService notificacaoService;

    @Operation(description = "Retorna todas as notificações registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificações encontradas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Nenhuma notificação encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @GetMapping
    public ResponseEntity<?> findAll () {
        try {
            List<Notificacao> notificacoes = notificacaoService.findAll();
            if (!notificacoes.isEmpty()) {
                return ResponseEntity.ok(notificacoes);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhuma notificação encontrado"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }
}
