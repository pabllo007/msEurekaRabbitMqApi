package br.com.pabllo007.cartoesMicroservice.mapper;

import br.com.pabllo007.cartoesMicroservice.dto.CartaoClienteDto;
import br.com.pabllo007.cartoesMicroservice.entities.CartaoCliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartaoClienteMapper {

    public CartaoClienteDto toDto(CartaoCliente cartaoCliente) {
        return new CartaoClienteDto(
                cartaoCliente.getCpf(),
                cartaoCliente.getCartao().getBandeira().toString(),
                cartaoCliente.getLimite()
        );
    }

    public List<CartaoClienteDto> toDtos(List<CartaoCliente> cartoesCliente) {
        return cartoesCliente.stream().map(cartaoCliente -> new CartaoClienteDto(
                cartaoCliente.getCpf(),
                cartaoCliente.getCartao().getBandeira().toString(),
                cartaoCliente.getLimite()
        )).collect(Collectors.toList());
    }
}
