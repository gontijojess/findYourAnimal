package com.gontijo.animal_service.controller;

import com.gontijo.animal_service.model.Animal;
import com.gontijo.animal_service.model.Mensagem;
import com.gontijo.animal_service.payload.MessagePayload;
import com.gontijo.animal_service.service.AnimalService;
import com.gontijo.animal_service.service.MensagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;
    private final MensagemService mensagemService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(required = false) Optional<String> cor) {
        try {
            List<Animal> pets = cor.map(animalService::filterByColor).orElseGet(animalService::findAll);
            if (pets.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhum resultado encontrado"));
            } else {
                return ResponseEntity.ok(pets);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }


    @GetMapping({"/{id}"})
    public ResponseEntity<?> findById (@PathVariable Long id){
        try {
            Optional<Animal> localizado =animalService.findById(id);
            if(localizado.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhum resultado encontrado"));
            } else {
                return ResponseEntity.ok(localizado);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<MessagePayload> create (@RequestBody Animal animal){
        try {
            animalService.save(animal);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso!"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<MessagePayload> update (@PathVariable Long id, @RequestBody Animal animalAlterado){
        try {
            animalService.update(id, animalAlterado);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<MessagePayload> delete (@PathVariable Long id){
        try {
            this.animalService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Deletado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @GetMapping("/ong/{id}")
    public ResponseEntity<List<Animal>> getAnimalsByOngId(@PathVariable Long id) {
        List<Animal> animais = animalService.findByOngId(id);
        if (animais.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(animais);
    }

    @GetMapping("/mensagens/animal/{id}")
    public ResponseEntity<List<Mensagem>> getMessageByAnimalId(@PathVariable Long id) {
        List<Mensagem> mensagens = mensagemService.getMessageByPetId(id);
        if (mensagens.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mensagens);
    }

}
