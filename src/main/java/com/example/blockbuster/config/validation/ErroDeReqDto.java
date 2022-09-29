package com.example.blockbuster.config.validation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroDeReqDto {

    private String campo;
    private String erro;

    public ErroDeReqDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }
}
