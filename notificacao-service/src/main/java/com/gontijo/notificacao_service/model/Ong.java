package com.gontijo.notificacao_service.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ong {
    private long id;
    private String nomeOng;
    private String nomeResponsavel;
    private String email;
    private String senha;
    private String telefone;
}
