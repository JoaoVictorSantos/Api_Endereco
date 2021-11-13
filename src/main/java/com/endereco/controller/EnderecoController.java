package com.endereco.controller;

import com.endereco.domain.convert.EnderecoConvert;
import com.endereco.domain.dto.EnderecoDTO;
import com.endereco.domain.entity.Endereco;
import com.endereco.domain.response.Response;
import com.endereco.service.EnderecoService;
import com.endereco.util.CepUtil;
import com.endereco.util.LoggerUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping(value = "/cep/{cep}", produces={"application/json"}, consumes="application/json")
    public ResponseEntity<Response<EnderecoDTO>> findByCep(@PathVariable("cep") String cep) {
        Logger logger = LoggerUtil.logger;
        logger.info("Endereco com cep: " + cep);

        Response<EnderecoDTO> response = new Response<>();
        try {
            Optional<Endereco> optional = service.findByCep(cep);
            if(optional.isPresent()) {
                response.setData(EnderecoConvert.toDTO(optional.get()));
                logger.info("Endereco com cep: " + cep + " foi encontrado");
                return ResponseEntity.ok(response);
            }else {
                logger.info(CepUtil.CEP_NOT_FOUND);
                response.setErro(CepUtil.CEP_NOT_FOUND);
            }
        }catch (Exception exception){
            logger.error(exception.getMessage());
            response.setErro(exception.getMessage());
        }

        return ResponseEntity.badRequest().body(response);
    }

}
