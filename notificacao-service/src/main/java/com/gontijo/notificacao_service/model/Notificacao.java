package com.gontijo.notificacao_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "NOTIFICACAO")
@Entity
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="ong_id")
    private long ongId;
    @Column(name="ong_name")
    private String ongName;
    @Column(name="ong_email")
    private String ongEmail;
    @Column(name="mensagem_id")
    private long mensagemId;
}
