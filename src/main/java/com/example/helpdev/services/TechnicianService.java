package com.example.helpdev.services;

import com.example.helpdev.domain.Person;
import com.example.helpdev.domain.Technician;
import com.example.helpdev.domain.dtos.TechnicianDTO;
import com.example.helpdev.exceptions.DataIntegrityViolationException;
import com.example.helpdev.exceptions.ObjectNotFoundException;
import com.example.helpdev.repositories.PersonRepository;
import com.example.helpdev.repositories.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository  repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Technician findBYId(Integer id){
        Optional<Technician> technician = repository.findById(id);
        return technician.orElseThrow(() -> new ObjectNotFoundException("Object Not Found! Id: " + id));
    }

    public List<Technician> findAll(){
        return repository.findAll();
    }

    public Technician create(TechnicianDTO technicianDTO){
        technicianDTO.setId(null);
        technicianDTO.setPassword(encoder.encode(technicianDTO.getPassword()));
        validateCpfAndEmail(technicianDTO);
        Technician newTechnician = new Technician(technicianDTO);
        return repository.save(newTechnician);
    }

    public Technician update(Integer id, TechnicianDTO technicianDTO) {
        technicianDTO.setId(id);
        Technician oldTechnician = findBYId(id);
        validateCpfAndEmail(technicianDTO);
        oldTechnician = new Technician(technicianDTO);
        return  repository.save(oldTechnician);
    }

    public void delete(Integer id) {
        Technician technician = findBYId(id);
        if(technician.getTickets().size() > 0){
            throw new DataIntegrityViolationException("The technician has associated tickets and cannot be deleted!");
        }
        repository.deleteById(id);
    }

    private  void validateCpfAndEmail(TechnicianDTO technicianDTO){
        Optional<Person> person = personRepository.findByCpf(technicianDTO.getCpf());
        if(person.isPresent() && person.get().getId() != technicianDTO.getId()){
            throw new DataIntegrityViolationException("CPF alredy registred in the sytem");
        }
        person = personRepository.findByEmail(technicianDTO.getEmail());
        if(person.isPresent() && person.get().getId() != technicianDTO.getId()){
            throw new DataIntegrityViolationException("Email alredy registred in the sytem");
        }
    }

}
