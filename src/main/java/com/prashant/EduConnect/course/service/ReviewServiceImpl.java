package com.prashant.EduConnect.course.service;

import com.prashant.EduConnect.course.dao.ReviewDao;
import com.prashant.EduConnect.course.entity.Review;
import com.prashant.EduConnect.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public Review findReviewById(int reviewId) {
        Review review = reviewDao.findReviewById(reviewId);
        if (review == null) {
            throw new ResourceNotFoundException("Review with id " + reviewId + " not found");
        }
        return review;
    }

    @Override
    public List<Review> findReviewByCourseId(int courseId) {
        List<Review> reviews = reviewDao.findReviewByCourseId(courseId);
        if (reviews == null || reviews.isEmpty()) {
            throw new ResourceNotFoundException("No courses found for instructor with id " + courseId);
        }
        return reviews;
    }

    @Override
    @Transactional
    public void updateReview(Review review) {
        if (review == null || review.getComment() == null || review.getComment().isEmpty()) {
            throw new IllegalArgumentException("Review comment cannot be null or empty");
        }
        if (reviewDao.findReviewById(review.getId()) == null) {
            throw new ResourceNotFoundException("Review with id " + review.getId() + " not found");
        }
        Review existingReview = reviewDao.findReviewById(review.getId());
        existingReview.setComment(review.getComment());
        reviewDao.updateReview(existingReview);
    }

    @Override
    @Transactional
    public void deleteReviewById(int reviewId) {
        Review review = reviewDao.findReviewById(reviewId);
        if (review == null) {
            throw new ResourceNotFoundException("Review with id " + reviewId + " not found");
        }
        reviewDao.deleteReviewById(reviewId);
    }
}
