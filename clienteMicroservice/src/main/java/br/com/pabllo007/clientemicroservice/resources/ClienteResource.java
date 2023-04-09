package br.com.pabllo007.clientemicroservice.resources;

import br.com.pabllo007.clientemicroservice.dto.ClienteDto;
import br.com.pabllo007.clientemicroservice.dto.ClienteSaveDto;
import br.com.pabllo007.clientemicroservice.entities.Cliente;
import br.com.pabllo007.clientemicroservice.exceptions.ClienteExistenteException;
import br.com.pabllo007.clientemicroservice.mapper.ClienteMapper;
import br.com.pabllo007.clientemicroservice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @GetMapping("/status")
    public String status(){
        return "ok";
    }


    @GetMapping(params = "cpf")
    ResponseEntity dadosCliente(@RequestParam("cpf") String cpf) {
        var cliente = clienteService.getByCPF(cpf);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ClienteDto clienteDto = clienteMapper.toDto(cliente.get());
        return ResponseEntity.ok(clienteDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<ClienteDto> getCliente(@PathVariable("id") Long id) {
        var cliente = clienteService.findById(id);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        ClienteDto clienteDto = clienteMapper.toDto(cliente.get());
        return ResponseEntity.ok(clienteDto);
    }


    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveDto clienteSaveDto) {

        Cliente cliente = clienteMapper.toEntity(clienteSaveDto);
        Optional<Cliente> clienteExistente = clienteService.getByCPF(cliente.getCpf());

        if (clienteExistente.isPresent()) {
            throw new ClienteExistenteException();
        }
        clienteService.save(cliente);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(clienteSaveDto.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }
}
