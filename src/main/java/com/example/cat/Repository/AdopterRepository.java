package com.example.cat.Repository;

import com.example.cat.Model.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter, Integer> {
    Adopter findAdopterById(Integer id);
    List<Adopter> findBySubscribedCatCareId(Integer catCareId);
    List<Adopter> findBySubscriptionEndDateBefore(LocalDate date);
}
