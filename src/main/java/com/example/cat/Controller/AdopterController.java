package com.example.cat.Controller;

import com.example.cat.Model.Adopter;
import com.example.cat.Service.AdopterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/adopters")
@RequiredArgsConstructor
public class AdopterController {

    private final AdopterService adopterService;

    @GetMapping("/get")
    public ResponseEntity getAllAdopters() {
        return ResponseEntity.status(HttpStatus.OK).body(adopterService.getAdopters());
    }

    @PostMapping("/add")
    public ResponseEntity addAdopter(@Valid @RequestBody Adopter adopter, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        adopterService.addAdopter(adopter);
        return ResponseEntity.status(HttpStatus.CREATED).body("Adopter added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAdopter(@PathVariable Integer id, @Valid @RequestBody Adopter adopter, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        adopterService.updateAdopter(id, adopter);
        return ResponseEntity.status(HttpStatus.OK).body("Adopter updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAdopter(@PathVariable Integer id) {
        adopterService.removeAdopter(id);
        return ResponseEntity.status(HttpStatus.OK).body("Adopter deleted successfully");
    }

    // Custom Endpoints

    @GetMapping("/catcare/{catCareId}")
    public ResponseEntity getAdoptersByCatCare(@PathVariable Integer catCareId) {
        return ResponseEntity.status(HttpStatus.OK).body(adopterService.getAdoptersByCatCare(catCareId));
    }

    @GetMapping("/expired-subscriptions")
    public ResponseEntity getExpiredSubscriptions() {
        return ResponseEntity.status(HttpStatus.OK).body(adopterService.getExpiredSubscriptions(LocalDate.now()));
    }

    @PostMapping("/subscribe/{id}/{catCareId}")
    public ResponseEntity subscribeToCatCare(@PathVariable Integer id, @PathVariable Integer catCareId) {
        adopterService.subscribeToCatCare(id, catCareId);
        return ResponseEntity.status(HttpStatus.OK).body("Subscribed to CatCare successfully");
    }
}
