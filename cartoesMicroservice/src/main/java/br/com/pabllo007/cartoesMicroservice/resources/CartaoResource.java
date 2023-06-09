package br.com.pabllo007.cartoesMicroservice.resources;

import br.com.pabllo007.cartoesMicroservice.dto.CartaoClienteDto;
import br.com.pabllo007.cartoesMicroservice.dto.CartaoDto;
import br.com.pabllo007.cartoesMicroservice.dto.CartaoSaveDto;
import br.com.pabllo007.cartoesMicroservice.entities.Cartao;
import br.com.pabllo007.cartoesMicroservice.entities.CartaoCliente;
import br.com.pabllo007.cartoesMicroservice.mapper.CartaoClienteMapper;
import br.com.pabllo007.cartoesMicroservice.mapper.CartaoMapper;
import br.com.pabllo007.cartoesMicroservice.services.CartaoService;
import br.com.pabllo007.cartoesMicroservice.services.ClienteCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
public class CartaoResource {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private ClienteCartaoService clienteCartaoService;
    @Autowired
    private CartaoMapper cartaoMapper;

    @Autowired
    private CartaoClienteMapper cartaoClienteMapper;

    @GetMapping("/status")
    public String getStatus() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastrarCartao(@RequestBody CartaoSaveDto cartaoSave) {
        var cartao = cartaoMapper.toEntity(cartaoSave);
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartaoClienteDto>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        List<CartaoCliente> list = clienteCartaoService.listaCartaoByCliente(cpf);
        return ResponseEntity.ok(cartaoClienteMapper.toDtos(list));

    }
    @GetMapping(params = "renda")
    public ResponseEntity<List<CartaoDto>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        List<Cartao> list = cartaoService.getCartaoRendaMenorIgual(renda);
        return ResponseEntity.ok(cartaoMapper.toDtos(list));
    }
}
