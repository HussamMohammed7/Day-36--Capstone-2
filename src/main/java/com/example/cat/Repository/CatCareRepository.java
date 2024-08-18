package com.example.cat.Repository;

import com.example.cat.Model.CatCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface CatCareRepository extends JpaRepository<CatCare,Integer> {
    CatCare findServiceById(Integer id);

}
