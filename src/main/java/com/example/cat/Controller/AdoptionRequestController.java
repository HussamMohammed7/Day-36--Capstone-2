package com.example.cat.Controller;

import com.example.cat.Model.AdoptionRequest;
import com.example.cat.Service.AdoptionRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/adoptionrequests")
@RequiredArgsConstructor
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionRequestService;

    @GetMapping("/get")
    public ResponseEntity getAllAdoptionRequests() {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionRequestService.getAdoptionRequests());
    }

    @PostMapping("/add")
    public ResponseEntity addAdoptionRequest(@Valid @RequestBody AdoptionRequest adoptionRequest, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        adoptionRequestService.addAdoptionRequest(adoptionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Adoption request added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateAdoptionRequest(@PathVariable Integer id, @Valid @RequestBody AdoptionRequest adoptionRequest, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        adoptionRequestService.updateAdoptionRequest(id, adoptionRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Adoption request updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAdoptionRequest(@PathVariable Integer id) {
        adoptionRequestService.removeAdoptionRequest(id);
        return ResponseEntity.status(HttpStatus.OK).body("Adoption request deleted successfully");
    }

    @GetMapping("/status/{status}")
    public ResponseEntity getAdoptionRequestsByStatus(@PathVariable String status) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionRequestService.getAdoptionRequestsByStatus(status));
    }

    @GetMapping("/foster/{fosterId}")
    public ResponseEntity getAdoptionRequestsByFosterId(@PathVariable Integer fosterId) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionRequestService.getAdoptionRequestsByFosterId(fosterId));
    }

    @GetMapping("/adopter/{adopterId}")
    public ResponseEntity getAdoptionRequestsByAdopterId(@PathVariable Integer adopterId) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionRequestService.getAdoptionRequestsByAdopterId(adopterId));
    }

    @GetMapping("/cat/{catId}")
    public ResponseEntity getAdoptionRequestsByCatId(@PathVariable Integer catId) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionRequestService.getAdoptionRequestsByCatId(catId));
    }

    @GetMapping("/completed/{adopterId}/{fosterId}")
    public ResponseEntity isAdoptionRequestCompleted(@PathVariable Integer adopterId, @PathVariable Integer fosterId) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionRequestService.isAdoptionRequestCompleted(adopterId, fosterId));
    }
    @PutMapping("/status/{id}")
    public ResponseEntity changeRequestStatus(@PathVariable Integer id, @RequestParam String status) {
        adoptionRequestService.changeRequestStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body("Adoption request status updated successfully");
    }

}
