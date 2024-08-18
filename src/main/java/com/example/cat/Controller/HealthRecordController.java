package com.example.cat.Controller;

import com.example.cat.Model.HealthRecord;
import com.example.cat.Service.HealthRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/healthrecords")
@RequiredArgsConstructor
public class HealthRecordController {

    private final HealthRecordService healthRecordService;

    // Endpoint to get all health records
    @GetMapping("/get")
    public ResponseEntity getAllHealthRecords() {
        return ResponseEntity.status(HttpStatus.OK).body(healthRecordService.getHealthRecords());
    }

    // Endpoint to add a new health record
    @PostMapping("/add")
    public ResponseEntity addHealthRecord(@Valid @RequestBody HealthRecord healthRecord, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        healthRecordService.addHealthRecord(healthRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body("Health record added successfully");
    }

    // Endpoint to update an existing health record
    @PutMapping("/update/{id}")
    public ResponseEntity updateHealthRecord(@PathVariable Integer id, @Valid @RequestBody HealthRecord healthRecord, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        healthRecordService.updateHealthRecord(id, healthRecord);
        return ResponseEntity.status(HttpStatus.OK).body("Health record updated successfully");
    }

    // Endpoint to delete a health record by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHealthRecord(@PathVariable Integer id) {
        healthRecordService.removeHealthRecord(id);
        return ResponseEntity.status(HttpStatus.OK).body("Health record deleted successfully");
    }

    // Custom endpoint to get health records by doctor ID
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity getHealthRecordsByDoctorId(@PathVariable String doctorId) {
        List<HealthRecord> records = healthRecordService.getHealthRecordsByDoctorId(doctorId);
        return ResponseEntity.status(HttpStatus.OK).body(records);
    }

    // Custom endpoint to get health records by type
    @GetMapping("/type/{type}")
    public ResponseEntity getHealthRecordsByType(@PathVariable String type) {
        List<HealthRecord> records = healthRecordService.getHealthRecordsByType(type);
        return ResponseEntity.status(HttpStatus.OK).body(records);
    }

    // Custom endpoint to get health records older than 6 months
    @GetMapping("/older-than-six-months")
    public ResponseEntity getHealthRecordsOlderThanSixMonths() {
        List<HealthRecord> records = healthRecordService.getHealthRecordsOlderThanSixMonths();
        return ResponseEntity.status(HttpStatus.OK).body(records);
    }
}
