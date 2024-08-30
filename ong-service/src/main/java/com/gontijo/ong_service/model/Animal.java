package com.gontijo.ong_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Animal {
    private long id;
    private String sexo;
    private String cor;
    private String porte;
    private String descricao;
    private String status;
    private String localDeResgate;
    private String urlFoto;
    private String especie;
    private int ongId;
}
