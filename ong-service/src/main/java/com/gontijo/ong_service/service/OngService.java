package com.gontijo.ong_service.service;

import com.gontijo.ong_service.model.Ong;

import java.util.List;
import java.util.Optional;

public interface OngService {
    List<Ong> findAll();

    Optional<Ong> findById(Long id);

    Ong save(Ong animal);

    Ong update(Long id, Ong ongAtualizado);
}
