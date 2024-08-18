package com.example.cat.Controller;

import com.example.cat.Model.CatCare;
import com.example.cat.Service.CatCareService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/catcare")
@RequiredArgsConstructor
public class CatCareController {

    private final CatCareService catCareService;

    @GetMapping("/get")
    public ResponseEntity getAllCatCareServices() {
        return ResponseEntity.status(HttpStatus.OK).body(catCareService.getCatCareServices());
    }

    @PostMapping("/add")
    public ResponseEntity addCatCare(@Valid @RequestBody CatCare catCare, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        catCareService.addCatCare(catCare);
        return ResponseEntity.status(HttpStatus.OK).body("Cat care service added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCatCare(@PathVariable Integer id, @Valid @RequestBody CatCare catCare, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        catCareService.updateCatCare(id, catCare);
        return ResponseEntity.status(HttpStatus.OK).body("Cat care service updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCatCare(@PathVariable Integer id) {
        catCareService.removeCatCare(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cat care service deleted successfully");
    }

    @PostMapping("/subscribe")
    public ResponseEntity subscribeToCatCare(
            @RequestParam Integer userId,
            @RequestParam Integer catCareId) {
        catCareService.subscribeToCatCare(userId, catCareId);
        return ResponseEntity.status(HttpStatus.OK).body("Subscription successful with a 20% discount for one month.");
    }
}
