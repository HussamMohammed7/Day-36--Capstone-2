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
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Report date cannot be null")
    @Column(columnDefinition = "date not null")
    private LocalDate reportDate = LocalDate.now();

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "text not null")
    private String description;

    @NotEmpty(message = "Location cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String location;

    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotEmpty(message = "Report type cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String reportType;  // e.g., "Lost Cat", "Emergency"
}
