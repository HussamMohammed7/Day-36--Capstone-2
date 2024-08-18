package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.Adopter;
import com.example.cat.Repository.AdopterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdopterService {

    private final AdopterRepository adopterRepository;

    public List<Adopter> getAdopters() {
        return adopterRepository.findAll();
    }

    public void addAdopter(Adopter adopter) {
        adopterRepository.save(adopter);
    }

    public void updateAdopter(Integer id, Adopter adopter) {
        Adopter existingAdopter = adopterRepository.findAdopterById(id);
        if (existingAdopter == null) {
            throw new ApiException("Adopter not found");
        }
        existingAdopter.setName(adopter.getName());
        existingAdopter.setEmail(adopter.getEmail());
        existingAdopter.setPhone(adopter.getPhone());
        existingAdopter.setPassword(adopter.getPassword());
        existingAdopter.setSubscribedCatCareId(adopter.getSubscribedCatCareId());
        existingAdopter.setSubscriptionEndDate(adopter.getSubscriptionEndDate());
        adopterRepository.save(existingAdopter);
    }

    public void removeAdopter(Integer id) {
        if (!adopterRepository.existsById(id)) {
            throw new ApiException("Adopter not found");
        }
        adopterRepository.deleteById(id);
    }

    public Adopter getAdopterById(Integer id) {
        Adopter adopter = adopterRepository.findAdopterById(id);
        if (adopter == null) {
            throw new ApiException("Adopter not found");
        }
        return adopter;
    }

    public void subscribeToCatCare(Integer adopterId, Integer catCareId) {
        Adopter adopter = getAdopterById(adopterId);
        adopter.setSubscribedCatCareId(catCareId);
        adopter.setSubscriptionEndDate(LocalDate.now().plusMonths(1));
        adopterRepository.save(adopter);
    }

    public List<Adopter> getAdoptersByCatCare(Integer catCareId) {
        return adopterRepository.findBySubscribedCatCareId(catCareId);
    }

    public List<Adopter> getExpiredSubscriptions(LocalDate date) {
        return adopterRepository.findBySubscriptionEndDateBefore(date);
    }
}
