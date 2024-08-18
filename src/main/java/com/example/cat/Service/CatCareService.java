package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.CatCare;
import com.example.cat.Model.Foster;
import com.example.cat.Repository.CatCareRepository;
import com.example.cat.Repository.FosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatCareService {

    private final CatCareRepository catCareRepository;
    private final FosterRepository fosterRepository;


    public List<CatCare> getCatCareServices() {
        return catCareRepository.findAll();
    }

    public void addCatCare(CatCare catCare) {
        catCareRepository.save(catCare);
    }

    public void updateCatCare(Integer id, CatCare catCare) {
        CatCare existingCatCare = catCareRepository.findServiceById(id);
        if (existingCatCare == null) {
            throw new ApiException("Service not found");
        }
        existingCatCare.setName(catCare.getName());
        existingCatCare.setDescription(catCare.getDescription());
        existingCatCare.setPrice(catCare.getPrice());
        existingCatCare.setCategory(catCare.getCategory());
        existingCatCare.setProviderName(catCare.getProviderName());
        existingCatCare.setProviderContact(catCare.getProviderContact());
        catCareRepository.save(existingCatCare);
    }

    public void removeCatCare(Integer id) {
        if (!catCareRepository.existsById(id)) {
            throw new ApiException("Service not found");
        }
        catCareRepository.deleteById(id);
    }

    public void subscribeToCatCare(Integer userId, Integer catCareId) {
        // Find the user
        Foster user = fosterRepository.findFosterById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }

        // Find the CatCare service
        CatCare catCare = catCareRepository.findById(catCareId)
                .orElseThrow(() -> new ApiException("CatCare service not found"));

        // Calculate discounted price
        Double discountedPrice = catCare.getPrice() * 0.8;

        // Set the subscription details
        user.setSubscribedCatCareId(catCareId);
        user.setSubscriptionEndDate(LocalDate.now().plusMonths(1)); // Subscription lasts one month

        // Save the updated user information
        fosterRepository.save(user);

        // Log the subscription or perform any additional actions
        System.out.println("User " + user.getName() + " subscribed to " + catCare.getName() +
                " until " + user.getSubscriptionEndDate() + " with a price of " + discountedPrice);
    }
}
