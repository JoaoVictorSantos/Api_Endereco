package com.endereco.repository;

import com.endereco.domain.entity.Endereco;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EnderecoRepositoryTest {

    private static final String CEP = "15370496";
    private static final String RUA = "Avenida Paulista - de 1300/1301 a 1498/1499";

    @Autowired
    private  EnderecoRepository repository;

    @Before
    public void setUp() {
        repository.save(getEndereco());
    }
    
    
    @Test
    public void findByCepTest(){
        Optional<Endereco> response = repository.findByCep(CEP);

        assertTrue(response.isPresent());
        assertNotNull(response.get());
        assertEquals(RUA, response.get().getRua());
    }


    private Endereco getEndereco(){
        Endereco endereco = new Endereco();
        endereco.setRua(RUA);
        endereco.setBairro("Sede");
        endereco.setCidade("São Paulo");
        endereco.setEstado("São Paulo");
        endereco.setCep(CEP);

        return endereco;
    }

}
