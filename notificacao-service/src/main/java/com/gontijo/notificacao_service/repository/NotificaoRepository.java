package com.gontijo.notificacao_service.repository;

import com.gontijo.notificacao_service.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificaoRepository extends JpaRepository<Notificacao, Long> {
}
