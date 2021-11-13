package com.endereco.service;

import com.endereco.domain.entity.Endereco;

import java.util.Optional;

public interface EnderecoService {

    Optional<Endereco> findByCep(String cep);
}
