package br.com.pabllo007.avaliadorcreditomicroservice.dto;


import lombok.Data;

@Data
public class DadosAvaliacaoDto {
    private String cpf;
    private Long renda;
}
