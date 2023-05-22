package com.example.helpdev.services;

import com.example.helpdev.domain.Person;
import com.example.helpdev.domain.Client;
import com.example.helpdev.domain.dtos.ClientDTO;
import com.example.helpdev.exceptions.DataIntegrityViolationException;
import com.example.helpdev.exceptions.ObjectNotFoundException;
import com.example.helpdev.repositories.PersonRepository;
import com.example.helpdev.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository  repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Client findBYId(Integer id){
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Object Not Found! Id: " + id));
    }

    public List<Client> findAll(){
        return repository.findAll();
    }

    public Client create(ClientDTO clientDTO){
        clientDTO.setId(null);
        validateCpfAndEmail(clientDTO);
        Client newClient = new Client(clientDTO);
        return repository.save(newClient);
    }

    public Client update(Integer id, ClientDTO clientDTO) {
        clientDTO.setId(id);
        clientDTO.setPassword(encoder.encode(clientDTO.getPassword()));
        Client oldClient = findBYId(id);
        validateCpfAndEmail(clientDTO);
        oldClient = new Client(clientDTO);
        return  repository.save(oldClient);
    }

    public void delete(Integer id) {
        Client client = findBYId(id);
        if(client.getTickets().size() > 0){
            throw new DataIntegrityViolationException("The client has associated tickets and cannot be deleted!");
        }
        repository.deleteById(id);
    }

    private  void validateCpfAndEmail(ClientDTO clientDTO){
        Optional<Person> person = personRepository.findByCpf(clientDTO.getCpf());
        if(person.isPresent() && person.get().getId() != clientDTO.getId()){
            throw new DataIntegrityViolationException("CPF alredy registred in the sytem");
        }
        person = personRepository.findByEmail(clientDTO.getEmail());
        if(person.isPresent() && person.get().getId() != clientDTO.getId()){
            throw new DataIntegrityViolationException("Email alredy registred in the sytem");
        }
    }

}
