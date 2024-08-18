package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.HealthRecord;
import com.example.cat.Repository.HealthRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthRecordService {

    private final HealthRecordRepository healthRecordRepository;

    public List<HealthRecord> getHealthRecords() {
        return healthRecordRepository.findAll();
    }

    public void addHealthRecord(HealthRecord healthRecord) {
        healthRecordRepository.save(healthRecord);
    }

    public void updateHealthRecord(Integer id, HealthRecord healthRecord) {
        HealthRecord existingRecord = healthRecordRepository.findHealthRecordById(id);
        if (existingRecord == null) {
            throw new ApiException("Health record not found");
        }
        existingRecord.setVaccinationDate(healthRecord.getVaccinationDate());
        existingRecord.setType(healthRecord.getType());
        existingRecord.setNotes(healthRecord.getNotes());
        healthRecordRepository.save(existingRecord);
    }

    public void removeHealthRecord(Integer id) {
        if (!healthRecordRepository.existsById(id)) {
            throw new ApiException("Health record not found");
        }
        healthRecordRepository.deleteById(id);
    }
    public List<HealthRecord> getHealthRecordsOlderThanSixMonths() {
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        return healthRecordRepository.findByVaccinationDateBefore(sixMonthsAgo);
    }
    public List<HealthRecord> getHealthRecordsByType(String type) {
        return healthRecordRepository.findByType(type);
    }
    public List<HealthRecord> getHealthRecordsByDoctorId(String doctorId) {
        return healthRecordRepository.findByDoctorId(doctorId);
    }


}
