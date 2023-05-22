package com.example.helpdev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.helpdev.domain.enums.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode(of = {"id", "cpf"})
@Entity
public abstract class Person implements Serializable {
    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String name;
    @Column(unique = true)
    protected String cpf;
    @Column(unique = true)
    protected String email;
    protected String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    protected Set<Integer> profiles = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate creationDate = LocalDate.now();

    public Person(){
        super();
        addProfile(Profile.CLIENT);
    }

    public Person(Integer id, String name, String cpf, String email, String password) {
        super();
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        addProfile(Profile.CLIENT);
    }

    public  Set<Profile> getProfiles(){
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile){
        this.profiles.add(profile.getCodigo());
    }
}