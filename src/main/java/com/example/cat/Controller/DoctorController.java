package com.example.cat.Controller;

import com.example.cat.Model.Doctor;
import com.example.cat.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/get")
    public ResponseEntity getAllDoctors() {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.getDoctors());
    }

    @PostMapping("/add")
    public ResponseEntity addDoctor(@Valid @RequestBody Doctor doctor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.addDoctor(doctor);
        return ResponseEntity.status(HttpStatus.OK).body("Doctor added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateDoctor(@PathVariable Integer id, @Valid @RequestBody Doctor doctor, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        doctorService.updateDoctor(id, doctor);
        return ResponseEntity.status(HttpStatus.OK).body("Doctor updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDoctor(@PathVariable Integer id) {
        doctorService.removeDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Doctor deleted successfully");
    }




}
