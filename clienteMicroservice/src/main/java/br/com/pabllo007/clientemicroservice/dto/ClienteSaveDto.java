package br.com.pabllo007.clientemicroservice.dto;

import lombok.Data;
@Data
public class ClienteSaveDto {
    private String cpf;
    private String nome;
    private Integer idade;

}
