package com.example.helpdev.domain;

import com.example.helpdev.domain.dtos.ClientDTO;
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
public class Client extends Person implements Serializable {
    private static final long serialVerionUID = 1L;
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets = new ArrayList<>();

    public Client(Integer id, String name, String cpf,  String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CLIENT);
    }

    public Client() {
        super();
        addProfile(Profile.CLIENT);
    }

    public Client(ClientDTO clientDTO) {
        super();
        this.id = clientDTO.getId();
        this.name = clientDTO.getName();
        this.cpf = clientDTO.getCpf();
        this.email = clientDTO.getEmail();
        this.password = clientDTO.getPassword();
        this.profiles = clientDTO.getProfiles().stream().map(x ->  x.getCodigo()).collect(Collectors.toSet());
        this.creationDate = clientDTO.getCreationDate();
    }
}
