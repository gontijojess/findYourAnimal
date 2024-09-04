package com.gontijo.ong_service.controller;

import com.gontijo.ong_service.model.Animal;
import com.gontijo.ong_service.model.Ong;

import com.gontijo.ong_service.payload.MessagePayload;
import com.gontijo.ong_service.service.AnimalService;
import com.gontijo.ong_service.service.OngService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class OngController {
    private final OngService ongService;
    private final AnimalService animalService;

    @GetMapping
    public ResponseEntity<?> findAll () {
        try {
            List<Ong> ongs = ongService.findAll();
            if (!ongs.isEmpty()) {
                return ResponseEntity.ok(ongs);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhuma ong encontrado"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> findById (@PathVariable Long id){
        log.info("Get Ong by Id: {}", id);
        try {
            Optional<Ong> localizado = ongService.findById(id);
            if (localizado.isPresent()) {
                return ResponseEntity.ok(localizado.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Ong não encontrada"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<MessagePayload> create (@RequestBody Ong ong){
        try {
            ongService.save(ong);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criada com sucesso!"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<MessagePayload> update (@PathVariable Long id, @RequestBody Ong ongAlterada){
        try {
            ongService.update(id, ongAlterada);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Atualizada com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @GetMapping("/{id}/animais")
    public ResponseEntity<List<Animal>> getAnimalsByOngId(@PathVariable Long id) {
        List<Animal> animais = animalService.getById(id);
        if (animais.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animais);
    }



}
