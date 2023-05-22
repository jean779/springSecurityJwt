package com.example.helpdev.domain;

import com.example.helpdev.domain.dtos.TechnicianDTO;
import com.example.helpdev.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Entity
public class Technician extends Person implements Serializable {
    private static final long serialVerionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Ticket> tickets = new ArrayList<>();

    public Technician(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addProfile(Profile.CLIENT);
    }

    public Technician(TechnicianDTO technicianDTO) {
        super();
        this.id = technicianDTO.getId();
        this.name = technicianDTO.getName();
        this.cpf = technicianDTO.getCpf();
        this.email = technicianDTO.getEmail();
        this.password = technicianDTO.getPassword();
        this.profiles = technicianDTO.getProfiles().stream().map(x ->  x.getCodigo()).collect(Collectors.toSet());
        this.creationDate = technicianDTO.getCreationDate();
    }

    public Technician() {
        super();
        addProfile(Profile.CLIENT);
    }

}
