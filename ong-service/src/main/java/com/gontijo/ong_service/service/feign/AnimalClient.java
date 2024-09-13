package com.gontijo.ong_service.service.feign;

import com.gontijo.ong_service.model.Animal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("ANIMAL-SERVICE")
public interface AnimalClient {
    @GetMapping("/ong")
    List<Animal> getAnimalsByOngId(@RequestParam("id") Long id);

    @GetMapping("/{id}")
    Animal getAnimalById(@PathVariable Long id);
}

