package com.example.cat.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @NotNull(message = "Age cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer age;

    @NotEmpty(message = "Breed cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String breed;

    @NotEmpty(message = "Gender cannot be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String gender;

    @NotEmpty(message = "Description cannot be empty")
    @Column(columnDefinition = "text not null")
    private String description;

    @Column(columnDefinition = "boolean not null default false")
    private boolean adopted;

    @Column(columnDefinition = "int not null")
    private Integer ownerId;

    @Column(columnDefinition = "date")
    private LocalDate createdAt = LocalDate.now();



}
