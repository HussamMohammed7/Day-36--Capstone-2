package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.Report;
import com.example.cat.Repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public List<Report> getReports() {
        return reportRepository.findAll();
    }

    public void addReport(Report report) {
        reportRepository.save(report);
    }

    public void updateReport(Integer id, Report report) {
        Report existingReport = reportRepository.findReportById(id);
        if (existingReport == null) {
            throw new ApiException("Report not found");
        }
        existingReport.setDescription(report.getDescription());
        existingReport.setLocation(report.getLocation());
        reportRepository.save(existingReport);
    }

    public void removeReport(Integer id) {
        if (!reportRepository.existsById(id)) {
            throw new ApiException("Report not found");
        }
        reportRepository.deleteById(id);
    }

    public void addLostCatReport(Report report) {
        report.setReportType("Lost Cat");
        reportRepository.save(report);
    }

    public void addEmergencyReport(Report report) {
        report.setReportType("Emergency");
        reportRepository.save(report);
    }

    public void notifyDoctorsNearby(Integer reportId) {
        Report report = reportRepository.findReportById(reportId);
        if (report == null) {
            throw new ApiException("Report not found");
        }
        if (!"Emergency".equals(report.getReportType())) {
            throw new ApiException("Notification can only be sent for emergency reports");
        }


        /////////////////////

        /////////////////////

        System.out.println("Notifying doctors near " + report.getLocation() + " about an emergency.");
    }

    public List<Report> getReportsByType(String reportType) {
        return reportRepository.findByReportType(reportType);
    }
}
