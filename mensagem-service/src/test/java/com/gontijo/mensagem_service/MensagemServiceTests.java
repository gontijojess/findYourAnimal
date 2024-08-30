package com.gontijo.mensagem_service;
import com.gontijo.mensagem_service.model.Mensagem;
import com.gontijo.mensagem_service.service.MensagemService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MensagemServiceTests {
    @Autowired
    MensagemService mensagemService;

    @Test
    @Transactional
    @DisplayName("Deve inserir uma mensagem no banco")
    public void testInsert() {
        List<Mensagem> all = mensagemService.findAll();
        int estadoInicial = all.size();
        Mensagem mensagem = new Mensagem();
        mensagem.setNome("Cláudia Diniz");
        mensagem.setEmail("claudia@gmail.com");
        mensagem.setTelefone("(51)99945-6345");
        mensagem.setTexto("Boa tarde, acredito que esse seja o meu gato. Ele tem uma mancha parecida. Posso ir dar uma olhada?");
        mensagem.setAnimalId(1);
        mensagemService.save(mensagem);
        all = mensagemService.findAll();
        int estadoFinal = all.size();
        assertEquals(estadoInicial + 1, estadoFinal);
    }

    @Test
    @Transactional
    @DisplayName("Deve excluir uma mensagem do banco")
    public void testDelete(){
        Mensagem mensagem = new Mensagem();
        mensagem.setNome("Cláudia Diniz");
        mensagem.setEmail("claudia@gmail.com");
        mensagem.setTelefone("(51)99945-6345");
        mensagem.setTexto("Boa tarde, acredito que esse seja o meu gato. Ele tem uma mancha parecida. Posso ir dar uma olhada?");
        mensagem.setAnimalId(1);
        mensagemService.save(mensagem);
        List<Mensagem> all = mensagemService.findAll();
        int estadoInicial = all.size();
        Mensagem mensagem2 = all.get(0);
        mensagemService.deleteById(mensagem2.getId());
        all = mensagemService.findAll();
        int estadoFinal = all.size();
        System.out.println(all);
        assertEquals(estadoInicial - 1, estadoFinal);
    }

    @Test
    @Transactional
    @DisplayName("Deve retornar as mensagens correspondentes ao animal pelo ID")
    public void getMessageByAnimalIdTest(){
        Mensagem mensagem = new Mensagem();
        mensagem.setNome("Cláudia Diniz");
        mensagem.setEmail("claudia@gmail.com");
        mensagem.setTelefone("(51)99945-6345");
        mensagem.setTexto("Boa tarde, acredito que esse seja o meu gato. Ele tem uma mancha parecida. Posso ir dar uma olhada?");
        mensagem.setAnimalId(1);
        mensagemService.save(mensagem);

        Mensagem mensagem2 = new Mensagem();
        mensagem2.setNome("Maurício Sales");
        mensagem2.setEmail("mauricio@gmail.com");
        mensagem2.setTelefone("(51)99967-4675");
        mensagem2.setTexto("Poderia me dar mais informações sobre esse cachorro? Pelo local acho que é o da minha vizinha");
        mensagem2.setAnimalId(5645645);
        mensagemService.save(mensagem2);

        Mensagem mensagem3 = new Mensagem();
        mensagem3.setNome("Ana Clara Miller");
        mensagem3.setEmail("ana_miller@gmail.com");
        mensagem3.setTelefone("(51)94565-9945");
        mensagem3.setTexto("Boa tarde, é o meu cachorro. Como posso fazer para pegá-lo?");
        mensagem3.setAnimalId(5645645);
        mensagemService.save(mensagem3);

        List<Mensagem> mensagens = mensagemService.findByAnimalId(5645645L);
        int mensagensSize = mensagens.size();

        assertEquals(2, mensagensSize);
        assertTrue(mensagens.contains(mensagem2));
        assertTrue(mensagens.contains(mensagem3));
        assertFalse(mensagens.contains(mensagem));

    }
}
