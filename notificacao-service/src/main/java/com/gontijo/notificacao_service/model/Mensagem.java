package com.gontijo.notificacao_service.model;

import com.gontijo.notificacao_service.model.enums.StatusMensagem;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mensagem {
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String texto;
    @Enumerated(EnumType.STRING)
    private StatusMensagem status;
    private LocalDateTime dataCriacao;
    private int animalId;
}
