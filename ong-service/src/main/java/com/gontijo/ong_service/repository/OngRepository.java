package com.gontijo.ong_service.repository;

import com.gontijo.ong_service.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OngRepository extends JpaRepository<Ong, Long> {
}
