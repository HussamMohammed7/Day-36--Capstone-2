package com.example.cat.Repository;

import com.example.cat.Model.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface HealthRecordRepository extends JpaRepository<HealthRecord, Integer> {

    HealthRecord findHealthRecordById(Integer id);
    List<HealthRecord> findByDoctorId(String doctorId);
    List<HealthRecord> findByType(String type);
    List<HealthRecord> findByVaccinationDateBefore(LocalDate date);

}
