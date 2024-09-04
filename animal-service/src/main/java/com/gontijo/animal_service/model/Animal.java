package com.gontijo.animal_service.model;

import com.gontijo.animal_service.model.enums.Especie;
import com.gontijo.animal_service.model.enums.Porte;
import com.gontijo.animal_service.model.enums.Sexo;
import com.gontijo.animal_service.model.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ANIMAL")
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String cor;
    @Enumerated(EnumType.STRING)
    private Porte porte;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name="local_de_resgate")
    private String localDeResgate;
    @Column(name="url_foto")
    private String urlFoto;
    @Enumerated(EnumType.STRING)
    private Especie especie;

    @Column(name="ong_id")
    private int ongId;

}
