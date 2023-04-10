package br.com.pabllo007.avaliadorcreditomicroservice.feignclients;

import br.com.pabllo007.avaliadorcreditomicroservice.dto.CartaoClienteDto;
import br.com.pabllo007.avaliadorcreditomicroservice.dto.DadosClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "mscartoes", path = "/cartoes")
public interface CartoesFeign {

    @GetMapping(params = "cpf")
    ResponseEntity<List<CartaoClienteDto>> getCartoesByCliente(@RequestParam("cpf") String cpf);

}
