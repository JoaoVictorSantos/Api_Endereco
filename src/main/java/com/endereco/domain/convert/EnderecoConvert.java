package com.endereco.domain.convert;

import com.endereco.domain.dto.EnderecoDTO;
import com.endereco.domain.entity.Endereco;

public class EnderecoConvert {

    public static EnderecoDTO toDTO(Endereco endereco){
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setRua(endereco.getRua());
        enderecoDTO.setBairro(endereco.getBairro());
        enderecoDTO.setCidade(endereco.getCidade());
        enderecoDTO.setEstado(endereco.getEstado());

        return enderecoDTO;
    }
}
