package com.example.helpdev.repositories;


import com.example.helpdev.domain.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
}
