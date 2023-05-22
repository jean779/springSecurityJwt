package com.example.helpdev.domain.dtos;


import com.example.helpdev.domain.Ticket;
import com.example.helpdev.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TicketDTO  implements Serializable {
    private static final long serialVerionUID = 1L;
    
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closeDate;
    @NotNull(message = "The 'priority' field is required.")
    private Integer priority;
    @NotNull(message = "The 'status' field is required.")
    private Integer status;
    @NotNull(message = "The 'title' field is required.")
    private String title;
    @NotNull(message = "The 'comments' field is required.")
    private String comments;
    @NotNull(message = "The 'technician' field is required.")
    private Integer technician;
    @NotNull(message = "The 'email' field is required.")
    private Integer client;
    private String nameTechnician;
    private String nameClient;

    public TicketDTO(Ticket ticket) {
        this.id = ticket.getId();
        this.openDate = ticket.getOpenDate();
        this.closeDate = ticket.getCloseDate();
        this.priority = ticket.getPriority().getCode();
        this.status = ticket.getStatus().getCode();
        this.title = ticket.getTitle();
        this.comments = ticket.getComments();
        this.technician = ticket.getTechnician().getId();
        this.client = ticket.getClient().getId();
        this.nameTechnician = ticket.getTechnician().getName();
        this.nameClient = ticket.getClient().getName();
    }
}
