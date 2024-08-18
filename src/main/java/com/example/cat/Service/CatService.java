package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.Cat;
import com.example.cat.Repository.CatRepository;
import com.example.cat.Repository.FosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;
    private final FosterRepository fosterRepository;

    public List<Cat> getCats() {
        return catRepository.findAll();
    }

    public void addCat(Cat cat) {
        if (fosterRepository.findById(cat.getOwnerId()).isEmpty()) {
            throw new ApiException("User not found");
        }
        catRepository.save(cat);
    }

    public void updateCat(Integer id, Cat cat) {
        Cat existingCat = catRepository.findCatById(id);
        if (existingCat == null) {
            throw new ApiException("Cat not found");
        }
        if (fosterRepository.findById(cat.getOwnerId()).isEmpty()) {
            throw new ApiException("User not found");
        }
        existingCat.setName(cat.getName());
        existingCat.setAge(cat.getAge());
        existingCat.setBreed(cat.getBreed());
        existingCat.setGender(cat.getGender());
        existingCat.setDescription(cat.getDescription());
        existingCat.setAdopted(cat.isAdopted());
        existingCat.setOwnerId(cat.getOwnerId());
        catRepository.save(existingCat);
    }

    public void removeCat(Integer id) {
        if (!catRepository.existsById(id)) {
            throw new ApiException("Cat not found");
        }
        catRepository.deleteById(id);
    }
    public List<Cat> getAdoptedCats() {
        return catRepository.findByAdoptedTrue();
    }

    public List<Cat> getAvailableCats() {
        return catRepository.findByAdoptedFalse();
    }
    public List<Cat> getCatsByAgeRange(Integer startAge, Integer endAge) {
        return catRepository.findByAgeBetween(startAge, endAge);
    }

    public List<Cat> getCatsByBreed(String breed) {
        return catRepository.findByBreed(breed);
    }

    public List<Cat> getCatsByGender(String gender) {
        return catRepository.findByGender(gender);
    }

    public List<Cat> getCatsByOwner(Integer ownerId) {
        return catRepository.findByOwnerId(ownerId);
    }
}
