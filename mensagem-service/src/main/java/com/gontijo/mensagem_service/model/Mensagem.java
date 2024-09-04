package com.gontijo.mensagem_service.model;

import com.gontijo.mensagem_service.model.enums.StatusMensagem;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MENSAGEM")
@Entity
@ToString
public class Mensagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String texto;
    @Enumerated(EnumType.STRING)
    private StatusMensagem status;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(name="data_criacao")
    private LocalDateTime dataCriacao;
    
    @Column(name="animal_id")
    private int animalId;

}