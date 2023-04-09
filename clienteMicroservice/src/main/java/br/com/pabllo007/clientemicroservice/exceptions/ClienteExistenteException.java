package br.com.pabllo007.clientemicroservice.exceptions;

public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException() {
        super("Já existe um cliente com este ID cadastrado.");
    }
}
