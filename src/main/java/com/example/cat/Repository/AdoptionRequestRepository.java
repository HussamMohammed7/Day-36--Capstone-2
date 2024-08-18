package com.example.cat.Repository;

import com.example.cat.Model.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Integer> {
    AdoptionRequest findAdoptionRequestById(Integer id);
    AdoptionRequest findByAdopterIdAndFosterIdAndStatus(Integer adopterId, Integer fosterId, String status);
    List<AdoptionRequest> findByStatus(String status);

    List<AdoptionRequest> findByFosterId(Integer fosterId);

    List<AdoptionRequest> findByAdopterId(Integer adopterId);

    List<AdoptionRequest> findByCatId(Integer catId);
}
