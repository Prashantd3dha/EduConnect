package com.prashant.EduConnect.course.rest;

import com.prashant.EduConnect.course.entity.Review;
import com.prashant.EduConnect.course.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable int reviewId){
        Review review = reviewService.findReviewById(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<List<Review>> getReviewByCourseId(@PathVariable int courseId){
        List<Review> reviews = reviewService.findReviewByCourseId(courseId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable int reviewId, @RequestBody Review review){
        review.setId(reviewId);
        reviewService.updateReview(review);
        return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable int reviewId) {
        reviewService.deleteReviewById(reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}
