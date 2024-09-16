package com.gontijo.ong_service.controller;
import com.gontijo.ong_service.model.Animal;
import com.gontijo.ong_service.model.Ong;
import com.gontijo.ong_service.payload.MessagePayload;
import com.gontijo.ong_service.service.AnimalService;
import com.gontijo.ong_service.service.OngService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@CrossOrigin(origins = "*")
public class OngController {
    private final OngService ongService;
    private final AnimalService animalService;

    @Operation(description = "Retorna todos as ongs registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ongs encontradas",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Nenhuma ong encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
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

    @Operation(description = "Retorna uma ong com base no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ong encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Nenhuma ong encontrada",
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

    @Operation(description = "Registra uma nova ong com as informações do RequestBody")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ong registrada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PostMapping
    public ResponseEntity<MessagePayload> create (@RequestBody Ong ong){
        try {
            ongService.save(ong);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MessagePayload("Criada com sucesso!"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(description = "Altera as informações de uma ong com base no id fornecido e RequestBody")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ong alterada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @PutMapping({"/{id}"})
    public ResponseEntity<MessagePayload> update (@PathVariable Long id, @RequestBody Ong ongAlterada){
        try {
            ongService.update(id, ongAlterada);
            return ResponseEntity.status(HttpStatus.OK).body(new MessagePayload("Atualizada com sucesso"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(description = "Retorna lista de animais com base no ID da ong fornecido")
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
    @GetMapping("/{id}/animais")
    public ResponseEntity<?> getAnimalsByOngId(@PathVariable Long id) {
        try {
            List<Animal> animais = animalService.getAnimalsByOngId(id);
            if (!animais.isEmpty()) {
                return ResponseEntity.ok(animais);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Animais não encontrados"));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }

    @Operation(description = "Retorna ong com base no ID da animal fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ong encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro. Nenhuma ong encontrada",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            ),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro. Tente novamente mais tarde ou entre em contato com o suporte.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessagePayload.class))}
            )
    })
    @GetMapping("/animal/{id}")
    public ResponseEntity<?> getOngByAnimalId(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        long ongId = animal.getOngId();
        Optional<Ong> ong = ongService.findById(ongId);
        try {
            if(ong.isPresent()){
                return ResponseEntity.ok(ong);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessagePayload("Ong não encontrada."));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessagePayload(ex.getMessage()));
        }
    }



}
