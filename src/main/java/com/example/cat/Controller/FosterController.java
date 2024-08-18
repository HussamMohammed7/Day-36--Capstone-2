package com.example.cat.Controller;

import com.example.cat.Model.Foster;
import com.example.cat.Service.FosterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fosters")
@RequiredArgsConstructor
public class FosterController {

    private final FosterService fosterService;

    @GetMapping("/get")
    public ResponseEntity getAllFosters() {
        return ResponseEntity.status(HttpStatus.OK).body(fosterService.getFosters());
    }

    @PostMapping("/add")
    public ResponseEntity addFoster(@Valid @RequestBody Foster foster, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        fosterService.addFoster(foster);
        return ResponseEntity.status(HttpStatus.CREATED).body("Foster added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateFoster(@PathVariable Integer id, @Valid @RequestBody Foster foster, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        fosterService.updateFoster(id, foster);
        return ResponseEntity.status(HttpStatus.OK).body("Foster updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFoster(@PathVariable Integer id) {
        fosterService.removeFoster(id);
        return ResponseEntity.status(HttpStatus.OK).body("Foster deleted successfully");
    }

    @GetMapping("/{id}/average-rating")
    public ResponseEntity getAverageRating(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(fosterService.getAverageRating(id));
    }

    @GetMapping("/blacklisted")
    public ResponseEntity getBlacklistedFosters() {
        return ResponseEntity.status(HttpStatus.OK).body(fosterService.getBlacklistedFosters());
    }

    @GetMapping("/top-rated")
    public ResponseEntity getTopRatedFosters(@RequestParam double threshold) {
        return ResponseEntity.status(HttpStatus.OK).body(fosterService.getTopRatedFosters(threshold));
    }

    @GetMapping("/verified")
    public ResponseEntity getVerifiedFosters() {
        return ResponseEntity.status(HttpStatus.OK).body(fosterService.getVerifiedFosters());
    }
    @GetMapping("/type")
    public ResponseEntity getFostersByType(@RequestParam String type) {
        return ResponseEntity.status(HttpStatus.OK).body(fosterService.getFostersByType(type));
    }

    @PutMapping("/change-availability/{id}")
    public ResponseEntity changeAvailability(@PathVariable Integer id) {
        fosterService.changeAvailability(id);
        return ResponseEntity.status(HttpStatus.OK).body("Foster availability changed to 'available'.");
    }
}
