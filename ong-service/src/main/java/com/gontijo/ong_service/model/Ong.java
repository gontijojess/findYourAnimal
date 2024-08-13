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
@Table(name = "ONG")
@Entity
public class Ong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="nome_ong")
    private String nomeOng;
    @Column(name="nome_responsavel")
    private String nomeResponsavel;
    private String email;
    private String senha;
    private String telefone;
}
