package com.endereco.controller;

import com.endereco.domain.entity.Endereco;
import com.endereco.service.EnderecoService;
import com.endereco.util.CepUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnderecoControllerTest {

    private static final String URL = "/endereco/cep/";

    @MockBean
    EnderecoService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void findByCep() throws Exception {
        Endereco endereco = getEndereco("Avenida Paulista - de 1300/1301 a 1498/1499", "Sede",
                "São Paulo","São Paulo", "15370496");
        BDDMockito.given(service.findByCep(Mockito.anyString()))
            .willReturn(Optional.of(endereco));

        mvc.perform(MockMvcRequestBuilders.get(URL + endereco.getCep())
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.rua").value(endereco.getRua()))
            .andExpect(jsonPath("$.data.cidade").value(endereco.getCidade()))
            .andExpect(jsonPath("$.data.bairro").value(endereco.getBairro()))
            .andExpect(jsonPath("$.data.estado").value(endereco.getEstado()));
    }

    @Test
    public void findByCepChangeByPositionTest() throws Exception {
        Endereco endereco = getEndereco("Rua Carlos Gomes - até 99998 - lado par", "Sede",
                "Pereira Barreto","São Paulo", "15370400");
        BDDMockito.given(service.findByCep("15370496"))
            .willReturn(Optional.of(endereco));

        mvc.perform(MockMvcRequestBuilders.get(URL + "15370496")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.rua").value(endereco.getRua()))
            .andExpect(jsonPath("$.data.cidade").value(endereco.getCidade()))
            .andExpect(jsonPath("$.data.bairro").value(endereco.getBairro()))
            .andExpect(jsonPath("$.data.estado").value(endereco.getEstado()));
    }

    @Test
    public void findByCepInvalidTest() throws Exception {
        BDDMockito.given(service.findByCep("153704960"))
            .willThrow(new RuntimeException(CepUtil.CEP_INVALIDO));

        mvc.perform(MockMvcRequestBuilders.get(URL + "153704960")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value(CepUtil.CEP_INVALIDO));
    }

    @Test
    public void findByCepNotFoundTest() throws Exception {
        BDDMockito.given(service.findByCep("15370498"))
                .willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.get(URL + "15370498")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value(CepUtil.CEP_NOT_FOUND));
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
