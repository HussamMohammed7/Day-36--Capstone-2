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
public class Foster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty")
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

    @NotEmpty(message = "Availability cannot be empty")
    @Column(columnDefinition = "varchar(255)")
    private String availability;

    @NotEmpty(message = "Address cannot be empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String address;

    @Column(columnDefinition = "int not null default 0")
    private int ratingCount;

    @Column(columnDefinition = "int not null default 0")
    private int ratingSum;

    @Column(columnDefinition = "boolean not null default false")
    private boolean blacklisted;

    @Column(columnDefinition = "boolean not null default false")
    private boolean verified;

    @Column(columnDefinition = "int not null default 0")
    private int warningCount;

    @Column(columnDefinition = "int")
    private Integer subscribedCatCareId;

    @Column(columnDefinition = "date")
    private LocalDate subscriptionEndDate;

    // New fields for foster type
    @Column(columnDefinition = "varchar(50) not null")
    private String fosterType;  // "hotel" or "foster"

    // Only relevant if fosterType is "hotel"
    @Column(columnDefinition = "decimal(10,2)")
    private Double price;

    @Column(columnDefinition = "date")
    private LocalDate fosterStartDate;

    @Column(columnDefinition = "date")
    private LocalDate fosterEndDate;

    // Method to calculate the average rating
    public double getAverageRating() {
        if (ratingCount == 0) {
            return 0.0;
        }
        return (double) ratingSum / ratingCount;
    }

    // Method to add a new rating
    public void addRating(int rating) {
        this.ratingSum += rating;
        this.ratingCount++;
    }
}
