package com.example.cat.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CatCare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Service name cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "text not null")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Column(columnDefinition = "decimal(10,2) not null")
    private Double price;

    @NotEmpty(message = "Category cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String category;  // Grooming, Cleaning

    @Column(columnDefinition = "varchar(255)")
    private String providerName;  // Optional: name of the provider or clinic offering the service

    @Column(columnDefinition = "varchar(255)")
    private String providerContact;  // Optional: contact information for the service provider
}
