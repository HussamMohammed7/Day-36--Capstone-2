package com.example.cat.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AdoptionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Request date cannot be null")
    @Column(columnDefinition = "date not null")
    private LocalDate requestDate;

    @NotEmpty(message = "Status cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String status;

    @Column(columnDefinition = "int not null")
    private Integer adopterId;

    @Column(columnDefinition = "int not null")
    private Integer fosterId;

    @Column(columnDefinition = "int not null")
    private Integer catId;
}
