package com.example.cat.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "int not null")
    private Integer reviewerId;

    @Column(columnDefinition = "int not null")
    private Integer subjectId;

    @NotEmpty(message = "Review cannot be empty")
    @Size(min = 10, max = 500, message = "Review must be between 10 and 500 characters")
    @Column(columnDefinition = "varchar(500) not null")
    private String content;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(columnDefinition = "int not null")
    private Integer rating;

    @Column(columnDefinition = "boolean not null default false")
    private boolean disputed;

    @Column(columnDefinition = "date")
    private LocalDate createdAt = LocalDate.now();
}
