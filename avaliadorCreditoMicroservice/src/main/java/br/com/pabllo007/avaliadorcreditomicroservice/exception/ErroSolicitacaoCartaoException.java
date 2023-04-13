package br.com.pabllo007.avaliadorcreditomicroservice.exception;

public class ErroSolicitacaoCartaoException extends RuntimeException {
    public ErroSolicitacaoCartaoException(String msg) {
        super(msg);
    }
}
