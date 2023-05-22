package com.example.helpdev.services;

import com.example.helpdev.domain.Client;
import com.example.helpdev.domain.Technician;
import com.example.helpdev.domain.Ticket;
import com.example.helpdev.domain.dtos.TicketDTO;
import com.example.helpdev.domain.enums.Priority;
import com.example.helpdev.domain.enums.Status;
import com.example.helpdev.exceptions.ObjectNotFoundException;
import com.example.helpdev.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private TechnicianService technicianService;

    @Autowired
    private ClientService clientService;

    public Ticket findById(Integer id){
        Optional<Ticket> ticket = repository.findById(id);
        return ticket.orElseThrow(() -> new ObjectNotFoundException("Object Not Found! ID:" + id));
    }

    public List<Ticket> findAll() {
        return  repository.findAll();
    }

    public Ticket create(TicketDTO ticketDTO) {
        return  repository.save(newTicket(ticketDTO));
    }

    public Ticket update(Integer id, TicketDTO ticketDTO) {
        ticketDTO.setId(id);
        Ticket oldTicket = findById(id);
        oldTicket = newTicket(ticketDTO);
        return  repository.save(oldTicket);
    }

    private Ticket newTicket(TicketDTO ticketDTO){
        Technician technician = technicianService.findBYId(ticketDTO.getTechnician());
        Client client = clientService.findBYId(ticketDTO.getClient());

        Ticket ticket = new Ticket();
        if(ticketDTO.getId() != null){
            ticket.setId(ticketDTO.getId());
        }
        if(ticketDTO.getStatus().equals(2)){
            ticket.setCloseDate(LocalDate.now());
        }
        ticket.setTechnician(technician);
        ticket.setClient(client);
        ticket.setPriority(Priority.toEnum(ticketDTO.getPriority()));
        ticket.setStatus(Status.toEnum(ticketDTO.getStatus()));
        ticket.setTitle(ticketDTO.getTitle());
        ticket.setComments(ticketDTO.getComments());
        return ticket;
    }

}
