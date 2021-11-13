package com.endereco.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Response<T> {

    private T data;
    private List<String> errors;

    public List<String> getErrors() {
        this.initErros();
        return this.errors;
    }

    public void setErro(String erro) {
        this.initErros();
        this.errors.add(erro);
    }

    private void initErros() {
        if(this.errors == null){
            this.errors = new ArrayList<String>();
        }
    }
}
