package com.example.helpdev.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.helpdev.domain.enums.Priority;
import com.example.helpdev.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Ticket  implements Serializable {
    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDate openDate = LocalDate.now();
    @JsonFormat(pattern = "dd/mm/yyyy")
    private LocalDate closeDate;
    private Priority priority;
    private Status status;
    private String title;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Ticket(Integer id, Priority priority, Status status,String title, String comments, Technician technician, Client client) {
        super();
        this.id = id;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.technician = technician;
        this.client = client;
        this.comments = comments;
    }


}
