package com.example.cat.Repository;

import com.example.cat.Model.Foster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FosterRepository extends JpaRepository<Foster, Integer> {
    Foster findFosterById(Integer id);

    List<Foster> findByVerified(boolean verified);

    List<Foster> findByFosterTypeAndPriceIsNotNull(String fosterType);

    List<Foster> findByFosterType(String fosterType);

    List<Foster> findByBlacklisted(boolean blacklisted);

    List<Foster> findByRatingSumGreaterThan(double threshold);

}
