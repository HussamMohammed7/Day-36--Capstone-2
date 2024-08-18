package com.example.cat.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Adopter {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 7, message = "Password must be more than 6 characters long")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).+$", message = "Password must contain both characters and digits")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;

    @NotEmpty(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    @Column(columnDefinition = "varchar(10) not null")
    private String phone;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Write a proper email")
    @Column(columnDefinition = "varchar(255) not null unique")
    private String email;



    @Column(columnDefinition = "int")
    private Integer subscribedCatCareId;

    @Column(columnDefinition = "date")
    private LocalDate subscriptionEndDate;
}
