package com.endereco.service;

import com.endereco.domain.entity.Endereco;
import com.endereco.repository.EnderecoRepository;
import com.endereco.util.CepUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EnderecoServiceTest {

    @MockBean
    EnderecoRepository repository;

    @Autowired
    EnderecoService service;

    @Test
    public void findByCepTest(){
        Endereco endereco = getEndereco("Avenida Paulista - de 1300/1301 a 1498/1499", "Sede",
                "São Paulo","São Paulo", "15370496");
        BDDMockito.given(repository.findByCep(Mockito.anyString()))
            .willReturn(Optional.of(endereco));

        Optional<Endereco> response = service.findByCep(endereco.getCep());
        assertTrue(response.isPresent());
        assertNotNull(response.get());
        assertEquals(endereco.getRua(), response.get().getRua());
        assertEquals(endereco.getBairro(), response.get().getBairro());
        assertEquals(endereco.getCidade(), response.get().getCidade());
        assertEquals(endereco.getEstado(), response.get().getEstado());
    }

    @Test
    public void findByCepChangeByPositionTest(){
        Endereco endereco = getEndereco("Rua Carlos Gomes - até 99998 - lado par", "Sede",
                "Pereira Barreto","São Paulo", "15370400");

        BDDMockito.given(repository.findByCep("15370400"))
                .willReturn(Optional.of(endereco));

        Optional<Endereco> response = service.findByCep("15370496");
        assertTrue(response.isPresent());
        assertNotNull(response.get());
        assertEquals(endereco.getRua(), response.get().getRua());
        assertEquals(endereco.getBairro(), response.get().getBairro());
        assertEquals(endereco.getCidade(), response.get().getCidade());
        assertEquals(endereco.getEstado(), response.get().getEstado());
    }

    @Test
    public void findByCepInvalid(){
        try {
            Optional<Endereco> response = service.findByCep("153704968");
        }catch (RuntimeException exception){
            assertEquals(CepUtil.CEP_INVALIDO, exception.getMessage());
        }
    }

    @Test
    public void findByCepNotFound(){
        Optional<Endereco> response = service.findByCep("00000000");
        assertFalse(response.isPresent());
    }

    private Endereco getEndereco(String rua, String bairro, String cidade, String estado, String cep){
        Endereco endereco = new Endereco();
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setCep(cep);

        return endereco;
    }
}
