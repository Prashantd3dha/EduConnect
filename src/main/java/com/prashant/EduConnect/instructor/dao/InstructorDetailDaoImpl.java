package com.prashant.EduConnect.instructor.dao;

import com.prashant.EduConnect.instructor.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InstructorDetailDaoImpl implements InstructorDetailDao{
    private final EntityManager entityManager;

    public InstructorDetailDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void update(InstructorDetail instructorDetail) {
        entityManager.merge(instructorDetail);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        if (instructorDetail != null) {
            entityManager.remove(instructorDetail);
        }
    }
}
