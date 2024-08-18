package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.Foster;
import com.example.cat.Repository.FosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FosterService {

    private final FosterRepository fosterRepository;

    public List<Foster> getFosters() {
        return fosterRepository.findAll();
    }

    public void addFoster(Foster foster) {
        fosterRepository.save(foster);
    }

    public void updateFoster(Integer id, Foster foster) {
        Foster existingFoster = fosterRepository.findFosterById(id);
        if (existingFoster == null) {
            throw new ApiException("Foster not found");
        }
        existingFoster.setName(foster.getName());
        existingFoster.setEmail(foster.getEmail());
        existingFoster.setPhone(foster.getPhone());
        existingFoster.setPassword(foster.getPassword());
        existingFoster.setSubscribedCatCareId(foster.getSubscribedCatCareId());
        existingFoster.setSubscriptionEndDate(foster.getSubscriptionEndDate());
        existingFoster.setFosterType(foster.getFosterType());
        existingFoster.setPrice(foster.getPrice());
        existingFoster.setFosterStartDate(foster.getFosterStartDate());
        existingFoster.setFosterEndDate(foster.getFosterEndDate());
        fosterRepository.save(existingFoster);
    }

    public void removeFoster(Integer id) {
        if (!fosterRepository.existsById(id)) {
            throw new ApiException("Foster not found");
        }
        fosterRepository.deleteById(id);
    }

    public double getAverageRating(Integer fosterId) {
        Foster foster = fosterRepository.findFosterById(fosterId);
        if (foster == null) {
            throw new ApiException("Foster not found");
        }
        if (foster.getRatingCount() == 0) {
            return 0.0;
        }
        return (double) foster.getRatingSum() / foster.getRatingCount();
    }


    private void checkAndUpdateBlacklist(Foster foster) {
        if (getAverageRating(foster.getId()) < 2) {
            foster.setBlacklisted(true);
        }
    }

    private void checkVerificationStatus(Foster foster) {
        if (getAverageRating(foster.getId()) >= 3) {
            foster.setVerified(true);
        }
    }

    private void checkWarnings(Foster foster) {
        if (getAverageRating(foster.getId()) < 2) {
            foster.setWarningCount(foster.getWarningCount() + 1);
            if (foster.getWarningCount() >= 3) {
                foster.setBlacklisted(true);
            }
        }
    }
    public void changeAvailability(Integer id) {
        Foster existingFoster = fosterRepository.findFosterById(id);
        if (existingFoster == null) {
            throw new ApiException("Foster not found");
        }
        existingFoster.setAvailability("available");
        fosterRepository.save(existingFoster);
    }

    public List<Foster> getHotelFostersWithPrice() {
        return fosterRepository.findByFosterTypeAndPriceIsNotNull("hotel");
    }

    public List<Foster> getFostersByType(String fosterType) {
        return fosterRepository.findByFosterType(fosterType);
    }


    public List<Foster> getBlacklistedFosters() {
        return fosterRepository.findByBlacklisted(true);
    }

    public List<Foster> getTopRatedFosters(double threshold) {
        return fosterRepository.findByRatingSumGreaterThan(threshold);
    }


    public List<Foster> getVerifiedFosters() {
        return fosterRepository.findByVerified(true);
    }
}
