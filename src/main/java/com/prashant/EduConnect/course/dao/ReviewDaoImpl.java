package com.prashant.EduConnect.course.dao;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.course.entity.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ReviewDaoImpl implements ReviewDao{
    private final EntityManager entityManager;

    public ReviewDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Review findReviewById(int reviewId) {
        return entityManager.find(Review.class, reviewId);
    }

    @Override
    public List<Review> findReviewByCourseId(int courseId) {
        TypedQuery<Review> query = entityManager.createQuery("from Review where course.id = :courseId", Review.class);
        query.setParameter("courseId",courseId);
        return query.getResultList();
    }

    @Override
    public void updateReview(Review review) {
        entityManager.merge(review);
    }

    @Override
    @Transactional
    public void deleteReviewById(int reviewId) {
        Review review = entityManager.find(Review.class, reviewId);
        entityManager.remove(review);
    }
}
