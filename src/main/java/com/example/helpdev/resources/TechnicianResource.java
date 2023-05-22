package com.example.helpdev.resources;

import com.example.helpdev.domain.Technician;
import com.example.helpdev.domain.dtos.TechnicianDTO;
import com.example.helpdev.services.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/technician")
public class TechnicianResource {
    @Autowired
    private TechnicianService  technicianService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id){
        Technician technician = technicianService.findBYId(id);
        return  ResponseEntity.ok().body(new TechnicianDTO(technician));
    }
    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll(){
        List<Technician> technicians = technicianService.findAll();
        List<TechnicianDTO> technicianDTOS = technicians.stream().map(obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(technicianDTOS);
    }
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO technicianDTO){
        Technician newTechnician = technicianService.create(technicianDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTechnician.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO technicianDTO){
        Technician technician= technicianService.update(id, technicianDTO);
        return ResponseEntity.ok().body(new TechnicianDTO(technician));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> delete(@PathVariable Integer id){
        technicianService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
