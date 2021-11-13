package com.endereco.service.impl;

import com.endereco.domain.entity.Endereco;
import com.endereco.repository.EnderecoRepository;
import com.endereco.service.EnderecoService;
import com.endereco.util.CepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    EnderecoRepository repository;

    @Override
    public Optional<Endereco> findByCep(String cep) throws RuntimeException{
        if(!CepUtil.isValidCep(cep)) throw new RuntimeException(CepUtil.CEP_INVALIDO);
        Optional<Endereco> optional = repository.findByCep(cep);

        if(!optional.isPresent() && !CepUtil.isAllZero(cep)){
            optional = findByCepChangeByPosition(cep);
        }

        return optional;
    }

    private Optional<Endereco> findByCepChangeByPosition(String cep) {
        Optional<Endereco> optional = Optional.empty();
        int index = cep.length() - 1;
        do {
            cep = CepUtil.changeCepByPosition(cep, index);
            optional = repository.findByCep(cep);
            if(CepUtil.isAllZero(cep)) break;
            index--;
        }while(index >= 0 && !optional.isPresent());

        return optional;
    }
}
