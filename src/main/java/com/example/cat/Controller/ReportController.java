package com.example.cat.Controller;

import com.example.cat.Model.Report;
import com.example.cat.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/get")
    public ResponseEntity getAllReports() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getReports());
    }

    @PostMapping("/add-lost-cat")
    public ResponseEntity addLostCatReport(@RequestBody Report report) {
        reportService.addLostCatReport(report);
        return ResponseEntity.status(HttpStatus.OK).body("Lost cat report added successfully");
    }

    @PostMapping("/add-emergency")
    public ResponseEntity addEmergencyReport(@RequestBody Report report) {
        reportService.addEmergencyReport(report);
        return ResponseEntity.status(HttpStatus.OK).body("Emergency report added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReport(@PathVariable Integer id, @RequestBody Report report) {
        reportService.updateReport(id, report);
        return ResponseEntity.status(HttpStatus.OK).body("Report updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReport(@PathVariable Integer id) {
        reportService.removeReport(id);
        return ResponseEntity.status(HttpStatus.OK).body("Report deleted successfully");
    }

    @GetMapping("/type/{reportType}")
    public ResponseEntity getReportsByType(@PathVariable String reportType) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getReportsByType(reportType));
    }

    @PostMapping("/notify-doctors/{id}")
    public ResponseEntity notifyDoctorsNearby(@PathVariable Integer id) {
        reportService.notifyDoctorsNearby(id);
        return ResponseEntity.status(HttpStatus.OK).body("Doctors notified about the emergency");
    }
}
