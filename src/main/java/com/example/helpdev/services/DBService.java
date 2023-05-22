package com.example.helpdev.services;

import com.example.helpdev.domain.Client;
import com.example.helpdev.domain.Technician;
import com.example.helpdev.domain.Ticket;
import com.example.helpdev.domain.enums.Priority;
import com.example.helpdev.domain.enums.Profile;
import com.example.helpdev.domain.enums.Status;
import com.example.helpdev.repositories.ClientRepository;
import com.example.helpdev.repositories.TechnicianRepository;
import com.example.helpdev.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){
        Technician technician = new Technician(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com", encoder.encode("123"));
        technician.addProfile(Profile.ADMIN);

        Client client = new Client(null, "Jean client", "12354546", "jesje232333332@gmail", encoder.encode("123"));
        client.addProfile(Profile.CLIENT);

        Ticket t1 = new Ticket(null, Priority.AVERAGE, Status.OPEN, "chamado 01", "comentários", technician, client);

        technicianRepository.saveAll(Arrays.asList(technician));
        clientRepository.saveAll(Arrays.asList(client));
        ticketRepository.saveAll(Arrays.asList(t1));
    }
}
