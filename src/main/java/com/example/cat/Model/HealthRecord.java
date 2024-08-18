package com.example.cat.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Vaccination date cannot be empty")
    @Column(columnDefinition = "date not null")
    private LocalDate vaccinationDate = LocalDate.now();

    @NotEmpty(message = "Type cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String type;

    @Column(columnDefinition = "text")
    private String notes;

    private String doctorId;
}
