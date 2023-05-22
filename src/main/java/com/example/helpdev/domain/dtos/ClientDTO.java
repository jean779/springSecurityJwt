package com.example.helpdev.domain.dtos;

import com.example.helpdev.domain.Client;
import com.example.helpdev.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO implements Serializable {
    private static final long serialVerionUID = 1L;
    protected Integer id;
    @NotNull(message = "The 'name' field is required.")
    protected String name;
    @NotNull(message = "The 'cpf' field is required.")
    @CPF
    protected String cpf;
    @NotNull(message = "The 'email' field is required.")
    protected String email;
    @NotNull(message = "The 'password' field is required.")
    protected String password;
    protected Set<Integer> profiles = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate creationDate = LocalDate.now();


    public ClientDTO(Client client) {
        super();
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.profiles = client.getProfiles().stream().map(x ->  x.getCodigo()).collect(Collectors.toSet());
        this.creationDate = client.getCreationDate();
        addProfile(Profile.CLIENT);
    }

    public  Set<Profile> getProfiles(){
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile){
        this.profiles.add(profile.getCodigo());
    }
}
