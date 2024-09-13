package com.gontijo.animal_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mensagem {
    private String nome;
    private String email;
    private String telefone;
    private String texto;
    private int animalId;
}
