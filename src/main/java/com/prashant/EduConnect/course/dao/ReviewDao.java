package com.prashant.EduConnect.course.dao;

import com.prashant.EduConnect.course.entity.Review;

import java.util.List;

public interface ReviewDao {
    Review findReviewById(int reviewId);
    List<Review> findReviewByCourseId(int courseId);
    void updateReview(Review review);
    void deleteReviewById(int reviewId);
}
