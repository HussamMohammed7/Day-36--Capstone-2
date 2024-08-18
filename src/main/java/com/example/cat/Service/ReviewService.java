package com.example.cat.Service;

import com.example.cat.Api.ApiException;
import com.example.cat.Model.AdoptionRequest;
import com.example.cat.Model.Foster;
import com.example.cat.Model.Review;
import com.example.cat.Model.Adopter;
import com.example.cat.Repository.AdoptionRequestRepository;
import com.example.cat.Repository.FosterRepository;
import com.example.cat.Repository.ReviewRepository;
import com.example.cat.Repository.AdopterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final FosterRepository fosterRepository;
    private final AdopterRepository adopterRepository;
    private final AdoptionRequestRepository adoptionRequestRepository;

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }

    public void addReview(Review review) {
        // Ensure the adopter exists
        Adopter adopter = adopterRepository.findAdopterById(review.getReviewerId());
        if (adopter == null) {
            throw new ApiException("Adopter not found");
        }

        // Ensure the foster exists
        Foster foster = fosterRepository.findFosterById(review.getSubjectId());
        if (foster == null) {
            throw new ApiException("Foster not found");
        }

        // Ensure there is a completed adoption request before allowing the review
        AdoptionRequest adoptionRequest = adoptionRequestRepository.findByAdopterIdAndFosterIdAndStatus(
                review.getReviewerId(), review.getSubjectId(), "completed"
        );
        if (adoptionRequest == null) {
            throw new ApiException("No completed adoption request found. Review cannot be made.");
        }

        // Save the review and update the foster's rating
        reviewRepository.save(review);
        addRatingToFoster(foster, review.getRating());
    }

    public void updateReview(Integer id, Review review) {
        Review existingReview = reviewRepository.findReviewById(id);
        if (existingReview == null) {
            throw new ApiException("Review not found");
        }

        Foster foster = fosterRepository.findFosterById(existingReview.getSubjectId());
        if (foster == null) {
            throw new ApiException("Foster not found");
        }

        // Adjust the foster's rating
        foster.setRatingSum(foster.getRatingSum() - existingReview.getRating() + review.getRating());

        existingReview.setContent(review.getContent());
        existingReview.setRating(review.getRating());

        reviewRepository.save(existingReview);
        fosterRepository.save(foster);
    }

    public void removeReview(Integer id) {
        Review existingReview = reviewRepository.findReviewById(id);
        if (existingReview == null) {
            throw new ApiException("Review not found");
        }

        Foster foster = fosterRepository.findFosterById(existingReview.getSubjectId());
        if (foster == null) {
            throw new ApiException("Foster not found");
        }

        // Adjust the foster's rating sum and count
        foster.setRatingSum(foster.getRatingSum() - existingReview.getRating());
        foster.setRatingCount(foster.getRatingCount() - 1);

        reviewRepository.deleteById(id);
        fosterRepository.save(foster);
    }

    private void addRatingToFoster(Foster foster, int rating) {
        foster.setRatingSum(foster.getRatingSum() + rating);
        foster.setRatingCount(foster.getRatingCount() + 1);
        fosterRepository.save(foster);
    }

    public void disputeReview(Integer id) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new ApiException("Review not found");
        }
        review.setDisputed(true);
        reviewRepository.save(review);
    }

    public void resolveDispute(Integer id, boolean keepReview) {
        Review review = reviewRepository.findReviewById(id);
        if (review == null) {
            throw new ApiException("Review not found");
        }
        if (keepReview) {
            review.setDisputed(false);
            reviewRepository.save(review);
        } else {
            reviewRepository.deleteById(id);
            Foster foster = fosterRepository.findFosterById(review.getSubjectId());
            if (foster != null) {
                foster.setRatingSum(foster.getRatingSum() - review.getRating());
                foster.setRatingCount(foster.getRatingCount() - 1);
                fosterRepository.save(foster);
            }
        }
    }
}
