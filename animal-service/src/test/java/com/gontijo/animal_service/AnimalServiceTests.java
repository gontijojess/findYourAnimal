package com.gontijo.animal_service;

import com.gontijo.animal_service.model.Animal;
import com.gontijo.animal_service.model.Porte;
import com.gontijo.animal_service.model.Sexo;
import com.gontijo.animal_service.model.Status;
import com.gontijo.animal_service.service.AnimalService;
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
public class AnimalServiceTests {
    @Autowired
    AnimalService animalService;

    @Test
    @Transactional
    @DisplayName("Deve retornar todos os animais do banco")
    public void testGetAll() {
        List<Animal> all = animalService.findAll();
        int estadoInicial = all.size();
        Animal animal1 = new Animal();
        animal1.setSexo(Sexo.FEMEA);
        animal1.setCor("Laranja");
        animal1.setPorte(Porte.MICRO);
        animal1.setDescricao("Testando");
        animal1.setStatus(Status.DISPONIVEL);
        animal1.setLocalDeResgate("Bairro das Flores");
        animal1.setUrlFoto("http://meupet.com/123");
        animal1.setEspecie("Gato");
        animal1.setOngId(1);
        animalService.save(animal1);

        Animal animal2 = new Animal();
            animal2.setSexo(Sexo.MACHO);
        animal2.setCor("Branco");
        animal2.setPorte(Porte.GRANDE);
        animal2.setDescricao("Encontrado no telhado");
        animal2.setStatus(Status.DISPONIVEL);
        animal2.setLocalDeResgate("Bairro dos Pinheiros");
        animal2.setUrlFoto("http://meupet.com/456");
        animal2.setEspecie("Cachorro");
        animal2.setOngId(2);
        animalService.save(animal2);

        all = animalService.findAll();
        int estadoFinal = all.size();

        List<Animal> allPets = animalService.findAll();
        assertEquals(estadoInicial + 2, estadoFinal);
        assertTrue(allPets.contains(animal1));
        assertTrue(allPets.contains(animal2));
    }

    @Test
    @DisplayName(value="Deve retornar um animal pelo ID")
    public void testGetById(){
        Animal animal = new Animal();
        animal.setSexo(Sexo.FEMEA);
        animal.setCor("Laranja");
        animal.setPorte(Porte.MICRO);
        animal.setDescricao("Testando");
        animal.setStatus(Status.DISPONIVEL);
        animal.setLocalDeResgate("Bairro das Flores");
        animal.setUrlFoto("http://meupet.com/123");
        animal.setEspecie("Gato");
        animal.setOngId(1);
        animalService.save(animal);
        List<Animal> all = animalService.findAll();
        Animal animal2 = all.get(0);
        Optional<Animal> byId = animalService.findById(animal2.getId());
        assertTrue(byId.isPresent());
    }

    @Test
    @DisplayName(value="Deve inserir um animal na lista")
    public void testInsert() {
        List<Animal> all = animalService.findAll();
        int estadoInicial = all.size();
        Animal animal = new Animal();
        animal.setSexo(Sexo.FEMEA);
        animal.setCor("Laranja");
        animal.setPorte(Porte.MICRO);
        animal.setDescricao("Testando");
        animal.setStatus(Status.DISPONIVEL);
        animal.setLocalDeResgate("Bairro das Flores");
        animal.setUrlFoto("http://meupet.com/123");
        animal.setEspecie("Gato");
        animal.setOngId(1);
        animalService.save(animal);
        all = animalService.findAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial + 1, estadoFinal);
    }

    @Test
    @DisplayName(value="Deve deletar um animal do banco")
    public void testDelete(){
        Animal animal = new Animal();
        animal.setSexo(Sexo.FEMEA);
        animal.setCor("Laranja");
        animal.setPorte(Porte.MICRO);
        animal.setDescricao("Testando");
        animal.setStatus(Status.DISPONIVEL);
        animal.setLocalDeResgate("Bairro das Flores");
        animal.setUrlFoto("http://meupet.com/123");
        animal.setEspecie("Gato");
        animal.setOngId(1);
        animalService.save(animal);
        List<Animal> all = animalService.findAll();
        int estadoInicial = all.size();
        Animal animal2 = all.get(0);
        animalService.deleteById(animal2.getId());
        all = animalService.findAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial - 1, estadoFinal);
    }

    @Test
    @DisplayName("Deve atualizar um animal no banco")
    public void testUpdate() {
        Animal animal = new Animal();
        animal.setSexo(Sexo.FEMEA);
        animal.setCor("Laranja");
        animal.setPorte(Porte.MICRO);
        animal.setDescricao("Testando");
        animal.setStatus(Status.DISPONIVEL);
        animal.setLocalDeResgate("Bairro das Flores");
        animal.setUrlFoto("http://meupet.com/123");
        animal.setEspecie("Gato");
        animal.setOngId(1);
        animalService.save(animal);

        List<Animal> all = animalService.findAll();
        int estadoInicial = all.size();

        Animal updatedAnimal = new Animal();
        updatedAnimal.setSexo(Sexo.FEMEA);
        updatedAnimal.setCor("Malhado");
        updatedAnimal.setPorte(Porte.PEQUENO);
        updatedAnimal.setDescricao("Encontrado com mais dois cachorros caramelo");
        updatedAnimal.setStatus(Status.DISPONIVEL);
        updatedAnimal.setLocalDeResgate("Bairro das Flores");
        updatedAnimal.setUrlFoto("http://meupet.com/123");
        updatedAnimal.setEspecie("Gato");
        updatedAnimal.setOngId(1);
        animalService.update(animal.getId(), updatedAnimal);

        List<Animal> allAnimais= animalService.findAll();
        int estadoFinal = allAnimais.size();
        Animal animais = allAnimais.get(allAnimais.size() - 1);
        assertEquals(   estadoInicial, estadoFinal);
        assertEquals(animal.getId(), updatedAnimal.getId());
        assertEquals("Encontrado com mais dois cachorros caramelo", animais.getDescricao());
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar todos os pets associados a um membro")
    public void testGetAllPetsForMember() {
        Animal animal1 = new Animal();
        animal1.setSexo(Sexo.FEMEA);
        animal1.setCor("Laranja");
        animal1.setPorte(Porte.MICRO);
        animal1.setDescricao("Estava em cima de uma casa azul de telhado branco");
        animal1.setStatus(Status.DISPONIVEL);
        animal1.setLocalDeResgate("Bairro das Flores");
        animal1.setUrlFoto("http://meupet.com/123");
        animal1.setEspecie("Gato");
        animal1.setOngId(1);
        animalService.save(animal1);

        Animal animal2 = new Animal();
        animal2.setSexo(Sexo.MACHO);
        animal2.setCor("Preto");
        animal2.setPorte(Porte.GIGANTE);
        animal2.setDescricao("Encontrado próximo da fazendo das andorinhas");
        animal2.setStatus(Status.DISPONIVEL);
        animal2.setLocalDeResgate("Bairro das Flores");
        animal2.setUrlFoto("http://meupet.com/789");
        animal2.setEspecie("Cavalo");
        animal2.setOngId(1);
        animalService.save(animal2);

        List<Animal> animaisForMember = animalService.findByOngId(1L);
        assertTrue(animaisForMember.contains(animal1));
        assertTrue(animaisForMember.contains(animal2));
        assertEquals("Estava em cima de uma casa azul de telhado branco", animaisForMember.get(animaisForMember.size() - 2).getDescricao());
        assertEquals(Sexo.MACHO, animaisForMember.get(animaisForMember.size() - 1).getSexo());
    }


}
