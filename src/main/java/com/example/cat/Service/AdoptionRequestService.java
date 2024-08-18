package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.Adopter;
import com.example.cat.Model.AdoptionRequest;
import com.example.cat.Model.Cat;
import com.example.cat.Model.Foster;
import com.example.cat.Repository.AdopterRepository;
import com.example.cat.Repository.AdoptionRequestRepository;
import com.example.cat.Repository.CatRepository;
import com.example.cat.Repository.FosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionRequestService {

    private final AdoptionRequestRepository adoptionRequestRepository;
    private final CatRepository catRepository;
    private final FosterRepository fosterRepository;
    private final AdopterRepository adopterRepository;

    // Get all adoption requests
    public List<AdoptionRequest> getAdoptionRequests() {
        return adoptionRequestRepository.findAll();
    }

    // Add a new adoption request
    public void addAdoptionRequest(AdoptionRequest adoptionRequest) {

        Cat cat = catRepository.findCatById(adoptionRequest.getCatId());
        Foster foster = fosterRepository.findFosterById(adoptionRequest.getFosterId());
        Adopter adopter = adopterRepository.findAdopterById(adoptionRequest.getAdopterId());

        if (cat == null){
            throw new ApiException("cat  not found");
        }
        if (foster == null){
            throw new ApiException("foster not found");
        }
        if (adopter == null){
            throw new ApiException("adopter not found");
        }

        adoptionRequestRepository.save(adoptionRequest);
    }

    // Update an existing adoption request
    public void updateAdoptionRequest(Integer id, AdoptionRequest adoptionRequest) {
        Cat cat = catRepository.findCatById(adoptionRequest.getCatId());
        Foster foster = fosterRepository.findFosterById(adoptionRequest.getFosterId());
        Adopter adopter = adopterRepository.findAdopterById(adoptionRequest.getAdopterId());

        AdoptionRequest existingRequest = adoptionRequestRepository.findAdoptionRequestById(id);
        if (cat == null){
            throw new ApiException("cat  not found");
        }
        if (foster == null){
            throw new ApiException("foster not found");
        }
        if (adopter == null){
            throw new ApiException("adopter not found");
        }

        if (existingRequest == null) {
            throw new ApiException("Adoption request not found");
        }
        existingRequest.setRequestDate(adoptionRequest.getRequestDate());
        existingRequest.setStatus(adoptionRequest.getStatus());
        adoptionRequestRepository.save(existingRequest);
    }

    // Remove an adoption request
    public void removeAdoptionRequest(Integer id) {
        if (!adoptionRequestRepository.existsById(id)) {
            throw new ApiException("Adoption request not found");
        }
        adoptionRequestRepository.deleteById(id);
    }

    // Find adoption requests by status
    public List<AdoptionRequest> getAdoptionRequestsByStatus(String status) {
        return adoptionRequestRepository.findByStatus(status);
    }

    // Find adoption requests by foster ID
    public List<AdoptionRequest> getAdoptionRequestsByFosterId(Integer fosterId) {
        return adoptionRequestRepository.findByFosterId(fosterId);
    }

    // Find adoption requests by adopter ID
    public List<AdoptionRequest> getAdoptionRequestsByAdopterId(Integer adopterId) {
        return adoptionRequestRepository.findByAdopterId(adopterId);
    }

    // Find adoption requests by cat ID
    public List<AdoptionRequest> getAdoptionRequestsByCatId(Integer catId) {
        return adoptionRequestRepository.findByCatId(catId);
    }

    // Check if there's a completed adoption request for a given adopter and foster
    public boolean isAdoptionRequestCompleted(Integer adopterId, Integer fosterId) {
        AdoptionRequest request = adoptionRequestRepository.findByAdopterIdAndFosterIdAndStatus(adopterId, fosterId, "completed");
        return request != null;
    }
    public void changeRequestStatus(Integer id, String status) {
        AdoptionRequest existingRequest = adoptionRequestRepository.findAdoptionRequestById(id);
        if (existingRequest == null) {
            throw new ApiException("Adoption request not found");
        }

        switch (status.toLowerCase()) {
            case "approve":
                existingRequest.setStatus("approved");
                break;
            case "reject":
                existingRequest.setStatus("rejected");
                break;
            case "completed":
                existingRequest.setStatus("completed");
                break;
            case "withdraw":
                existingRequest.setStatus("withdrawn");
                break;
            default:
                throw new ApiException("Invalid status");
        }

        adoptionRequestRepository.save(existingRequest);
    }
}
