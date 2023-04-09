package br.com.pabllo007.clientemicroservice.mapper;

import br.com.pabllo007.clientemicroservice.dto.ClienteDto;
import br.com.pabllo007.clientemicroservice.dto.ClienteSaveDto;
import br.com.pabllo007.clientemicroservice.entities.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteSaveDto clienteSaveDto) {
        return new Cliente(clienteSaveDto.getCpf(), clienteSaveDto.getNome(), clienteSaveDto.getIdade());
    }


    public ClienteDto toDto(Cliente cliente) {
        return new ClienteDto(cliente.getId(), cliente.getCpf(), cliente.getNome(), cliente.getIdade());
    }

}
