package com.gontijo.notificacao_service.service.feign;
import com.gontijo.notificacao_service.model.Mensagem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@FeignClient("MENSAGEM-SERVICE")
public interface MensagemClient {
    @GetMapping({"/{id}"})
    Optional<Mensagem> getById(@PathVariable Long id);
}
