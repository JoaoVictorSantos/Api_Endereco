package com.endereco.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EnderecoDTO {

    private String rua;
    private String bairro;
    private String cidade;
    private String estado;

}
