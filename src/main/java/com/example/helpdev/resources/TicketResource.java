package com.example.helpdev.resources;

import com.example.helpdev.domain.Ticket;
import com.example.helpdev.domain.dtos.TicketDTO;
import com.example.helpdev.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tickets")
public class TicketResource {

    @Autowired
    private TicketService ticketService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketDTO> findbyid(@PathVariable Integer id){
        Ticket ticket = ticketService.findById(id);
        return  ResponseEntity.ok().body(new TicketDTO(ticket));
    }
    @GetMapping
    public  ResponseEntity<List<TicketDTO>> findall(){
        List<Ticket> tickets = ticketService.findAll();
        List<TicketDTO> ticketDTOS = tickets.stream().map(ticket -> new TicketDTO(ticket)).collect(Collectors.toList());
        return ResponseEntity.ok().body(ticketDTOS);
    }
    @PostMapping
    public ResponseEntity<TicketDTO> create(@Valid @RequestBody TicketDTO ticketDTO){
        Ticket ticket = ticketService.create(ticketDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(ticketDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<TicketDTO> update(@PathVariable Integer id,@Valid @RequestBody TicketDTO ticketDTO){
        Ticket ticket = ticketService.update(id, ticketDTO);
        return ResponseEntity.ok().body(new TicketDTO(ticket));
    }

}
