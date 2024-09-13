package com.gontijo.notificacao_service.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String status;
    private LocalDateTime dataCriacao;
    private int animalId;
}
