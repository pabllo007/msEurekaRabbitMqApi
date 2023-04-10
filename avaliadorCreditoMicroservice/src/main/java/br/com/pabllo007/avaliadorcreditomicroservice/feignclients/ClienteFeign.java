package br.com.pabllo007.avaliadorcreditomicroservice.feignclients;

import br.com.pabllo007.avaliadorcreditomicroservice.dto.DadosClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msclientes", path = "/clientes")
public interface ClienteFeign {
    @GetMapping(params = "cpf")
    ResponseEntity<DadosClienteDto> dadosCliente(@RequestParam("cpf") String cpf);

}
