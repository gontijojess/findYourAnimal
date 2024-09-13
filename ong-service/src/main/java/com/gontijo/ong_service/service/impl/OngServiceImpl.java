package com.gontijo.ong_service.service.impl;
import com.gontijo.ong_service.model.Ong;
import com.gontijo.ong_service.repository.OngRepository;
import com.gontijo.ong_service.service.OngService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OngServiceImpl implements OngService {
    private final OngRepository ongRepository;

    @Override
    public List<Ong> findAll() {
        return ongRepository.findAll();
    }

    @Override
    public Optional<Ong> findById(Long id) {
        return ongRepository.findById(id);
    }

    @Override
    public Ong save(Ong ong) {
        return ongRepository.save(ong);
    }

    @Override
    public Ong update(Long id, Ong ongAtualizada) {
        ongAtualizada.setId(id);
        return ongRepository.save(ongAtualizada);
    }

}
