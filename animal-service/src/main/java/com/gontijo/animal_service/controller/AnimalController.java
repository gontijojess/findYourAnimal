package com.gontijo.animal_service.controller;
import com.gontijo.animal_service.model.Animal;
import com.gontijo.animal_service.model.Mensagem;
import com.gontijo.animal_service.model.enums.Status;
import com.gontijo.animal_service.payload.MessagePayload;
import com.gontijo.animal_service.service.AnimalService;
import com.gontijo.animal_service.service.MensagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnimalController {
    private final AnimalService animalService;
    private final MensagemService mensagemService;

    @Operation(description = "Retorna todos os animais registrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animais encontrados",
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = MessagePayload.class))}
        ),
        @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Nenhum animal encontrado",
                content = {@Content(mediaType = "application/json",
                        schema = @Schema(implementation = MessagePayload.class))}
        ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
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

    @Operation(description = "Retorna um animal com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Animal não encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
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

    @Operation(description = "Registra um novo animal com as informações do RequestBody")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Animal criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping
    public ResponseEntity<MessagePayload> create (@RequestBody Animal animal){
        try {
            animal.setStatus(Status.DISPONIVEL);
            animalService.save(animal);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criado com sucesso!"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(description = "Altera as informações de um animal com base no id fornecido e RequestBody")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal alterado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PutMapping({"/{id}"})
    public ResponseEntity<MessagePayload> update (@PathVariable Long id, @RequestBody Animal animalAlterado){
        try {
            animalService.update(id, animalAlterado);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Atualizado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(description = "Deleta registro de animal por meio do ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animal deletado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @DeleteMapping({"/{id}"})
    public ResponseEntity<MessagePayload> delete (@PathVariable Long id){
        try {
            this.animalService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Deletado com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Animais encontrados",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Nenhum animal não encontrado para essa organização",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })

    @Operation(description = "Retorna lista de animais com base no ID da ong fornecido")
    @GetMapping("/ong")
    public ResponseEntity<?> getAnimalsByOngId(@RequestParam Long id) {
        try {
            List<Animal> animais = animalService.findByOngId(id);
            if (animais.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhum animal encontrado"));
            } else {
                return ResponseEntity.ok(animais);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(description = "Retorna lista de mensagens com base no ID do animal fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagens encontradas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Nenhum mensagem encontrada para esse animal",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @GetMapping("/{id}/mensagens")
    public ResponseEntity<?> getMessageByAnimalId(@PathVariable Long id) {
        try {
            List<Mensagem> mensagens = mensagemService.getById(id);
            if (mensagens.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Nenhuma mensagem encontrada"));
            }
            return ResponseEntity.ok(mensagens);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }
}
