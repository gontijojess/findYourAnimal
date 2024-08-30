package com.gontijo.ong_service.payload;

import com.gontijo.ong_service.model.Animal;

import java.util.List;

public record AnimalPayload(List<Animal> animals){};