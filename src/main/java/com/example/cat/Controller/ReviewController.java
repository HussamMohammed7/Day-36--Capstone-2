package com.example.cat.Controller;

import com.example.cat.Model.Review;
import com.example.cat.Service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get")
    public ResponseEntity getAllReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getReviews());
    }

    @PostMapping("/add")
    public ResponseEntity addReview(@Valid @RequestBody Review review, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.OK).body("Review added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateReview(@PathVariable Integer id, @Valid @RequestBody Review review, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        reviewService.updateReview(id, review);
        return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteReview(@PathVariable Integer id) {
        reviewService.removeReview(id);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully");
    }

    @PostMapping("/dispute/{id}")
    public ResponseEntity disputeReview(@PathVariable Integer id) {
        reviewService.disputeReview(id);
        return ResponseEntity.status(HttpStatus.OK).body("Review disputed successfully");
    }

    @PostMapping("/resolve-dispute/{id}")
    public ResponseEntity resolveDispute(@PathVariable Integer id, @RequestParam boolean keepReview) {
        reviewService.resolveDispute(id, keepReview);
        return ResponseEntity.status(HttpStatus.OK).body("Dispute resolved successfully");
    }
}
