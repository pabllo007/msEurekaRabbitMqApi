package br.com.pabllo007.avaliadorcreditomicroservice.exception;

import lombok.Getter;

public class ErroComunicacaoMicroserviceException extends Exception {
    @Getter
    private Integer status;

    public ErroComunicacaoMicroserviceException(String msg, Integer status){
        super(msg);
        this.status = status;
    }
}
