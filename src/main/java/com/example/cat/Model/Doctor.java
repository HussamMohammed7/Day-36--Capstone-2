package com.example.cat.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Doctor {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Write a proper email")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;

    @NotEmpty(message = "Specialization cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String specialization;

    @NotEmpty(message = "Clinic address cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String clinicAddress;






}
