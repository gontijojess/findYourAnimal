package com.gontijo.ong_service;

import com.gontijo.ong_service.model.Ong;
import com.gontijo.ong_service.service.OngService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OngServiceTests {
    @Autowired
    OngService ongService;

    @Test
    @Transactional
    @DisplayName("Deve retornar todos os usuários do banco")
    public void testGetAll(){
        List<Ong> all = ongService.findAll();
        int estadoInicial = all.size();
        Ong ong1 = new Ong();
        ong1.setNomeOng("Abrigo fauna e flora");
        ong1.setNomeResponsavel("Maria Clara Azevedo");
        ong1.setEmail("maria_clara@gmail.com");
        ong1.setSenha("123456*!ABC");
        ong1.setTelefone("(51) 99326-4563");
        ongService.save(ong1);

        Ong ong2 = new Ong();
        ong2.setNomeOng("Toca segura");
        ong2.setNomeResponsavel("Marcos Augusto Silva");
        ong2.setEmail("msilva@gmail.com");
        ong2.setSenha("123456*!ABC");
        ong2.setTelefone("(51) 94566-4563");
        ongService.save(ong2);

        all = ongService.findAll();
        int estadoFinal = all.size();

        List<Ong> allPets = ongService.findAll();
        assertEquals(estadoInicial + 2, estadoFinal);
        assertTrue(allPets.contains(ong1));
        assertTrue(allPets.contains(ong2));
    }

    @Test
    @DisplayName("Deve retornar uma ong pelo ID")
    @Transactional
    public void testGetById(){
        Ong ong1 = new Ong();
        ong1.setNomeOng("Abrigo fauna e flora");
        ong1.setNomeResponsavel("Maria Clara Azevedo");
        ong1.setEmail("maria_clara@gmail.com");
        ong1.setSenha("123456*!ABC");
        ong1.setTelefone("(51) 99326-4563");
        ongService.save(ong1);
        Optional<Ong> byId = ongService.findById(ong1.getId());
        assertTrue(byId.isPresent());
        assertEquals(ong1, byId.get());
    }

    @Test
    @DisplayName("Deve inserir uma ong no banco")
    @Transactional
    public void testInsert(){
        List<Ong> all = ongService.findAll();
        int estadoInicial = all.size();
        Ong ong = new Ong();
        ong.setNomeOng("Abrigo fauna e flora");
        ong.setNomeResponsavel("Maria Clara Azevedo");
        ong.setEmail("maria_clara@gmail.com");
        ong.setSenha("123456*!ABC");
        ong.setTelefone("(51) 99326-4563");
        ongService.save(ong);
        all = ongService.findAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial + 1, estadoFinal);
    }

    @Test
    @DisplayName("Deve atualizar uma ong no banco")
    @Transactional
    public void testUpdate(){
        Ong ong = new Ong();
        ong.setNomeOng("Abrigo fauna e flora");
        ong.setNomeResponsavel("Maria Clara Azevedo");
        ong.setEmail("maria_clara@gmail.com");
        ong.setSenha("123456*!ABC");
        ong.setTelefone("(51) 99326-4563");
        ongService.save(ong);

        List<Ong> all = ongService.findAll();
        int estadoInicial = all.size();

        Ong updatedOng = new Ong();
        updatedOng.setNomeOng("Abrigo fauna natureza");
        updatedOng.setNomeResponsavel("Maria Clara Azevedo");
        updatedOng.setEmail("maria_clara@gmail.com");
        updatedOng.setSenha("123456*!ABC");
        updatedOng.setTelefone("(51) 99326-4563");
        ongService.update(ong.getId(), updatedOng);

        List<Ong> allOngs = ongService.findAll();
        int estadoFinal = allOngs.size();
        String nomeOng = ongService.findById(ong.getId()).get().getNomeOng();
        assertEquals(estadoInicial, estadoFinal);
        assertEquals(ong.getId(), updatedOng.getId());
        assertEquals("Abrigo fauna natureza", nomeOng);
    }



}
