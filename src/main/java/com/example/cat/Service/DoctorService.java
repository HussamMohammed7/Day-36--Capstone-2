package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.Doctor;
import com.example.cat.Repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void updateDoctor(Integer id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findDoctorById(id);
        if (existingDoctor == null) {
            throw new ApiException("Doctor not found");
        }
        existingDoctor.setName(doctor.getName());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setPhone(doctor.getPhone());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setClinicAddress(doctor.getClinicAddress());
        doctorRepository.save(existingDoctor);
    }

    public void removeDoctor(Integer id) {
        if (!doctorRepository.existsById(id)) {
            throw new ApiException("Doctor not found");
        }
        doctorRepository.deleteById(id);
    }
}
