package com.example.cat.Repository;

import com.example.cat.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findDoctorById(Integer id);

}
