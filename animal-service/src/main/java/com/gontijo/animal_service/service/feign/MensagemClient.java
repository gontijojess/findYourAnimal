package com.gontijo.animal_service.service.feign;

import com.gontijo.animal_service.model.Mensagem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("MENSAGEM-SERVICE")
public interface MensagemClient {
    @GetMapping("/animal/{id}")
    List<Mensagem> getById(@PathVariable("id") Long id);

}
