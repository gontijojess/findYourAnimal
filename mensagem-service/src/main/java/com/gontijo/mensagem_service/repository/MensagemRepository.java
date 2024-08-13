package com.gontijo.mensagem_service.repository;

import com.gontijo.mensagem_service.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findMensagemByAnimalId(Long id);
}
