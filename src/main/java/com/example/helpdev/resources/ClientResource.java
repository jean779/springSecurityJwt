package com.example.helpdev.resources;

import com.example.helpdev.domain.Client;
import com.example.helpdev.domain.dtos.ClientDTO;
import com.example.helpdev.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/client")
public class ClientResource {
    @Autowired
    private ClientService  clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Integer id){
        Client client = clientService.findBYId(id);
        return  ResponseEntity.ok().body(new ClientDTO(client));
    }
    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        List<Client> clients = clientService.findAll();
        List<ClientDTO> clientDTOS = clients.stream().map(obj -> new ClientDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientDTOS);
    }
    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDTO){
        Client newClient = clientService.create(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO clientDTO){
        Client client= clientService.update(id, clientDTO);
        return ResponseEntity.ok().body(new ClientDTO(client));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> delete(@PathVariable Integer id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
