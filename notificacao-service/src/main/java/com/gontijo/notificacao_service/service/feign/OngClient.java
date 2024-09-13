package com.gontijo.notificacao_service.service.feign;
import com.gontijo.notificacao_service.model.Ong;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ONG-SERVICE")
public interface OngClient {
    @GetMapping({"/animal/{id}"})
    ResponseEntity<Ong> getByAnimalId(@PathVariable Long id);
}