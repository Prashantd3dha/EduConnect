package com.prashant.EduConnect.instructor.service;

import com.prashant.EduConnect.exception.ResourceNotFoundException;
import com.prashant.EduConnect.instructor.dao.InstructorDetailDao;
import com.prashant.EduConnect.instructor.entity.InstructorDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstructorDetailServiceImpl implements InstructorDetailService{
    private final InstructorDetailDao instructorDetailDao;

    public InstructorDetailServiceImpl(InstructorDetailDao instructorDetailDao) {
        this.instructorDetailDao = instructorDetailDao;
    }

    @Override
    @Transactional
    public void update(InstructorDetail instructorDetail) {
        if (instructorDetailDao.findInstructorDetailById(instructorDetail.getId()) == null) {
            throw new ResourceNotFoundException("InstructorDetail with id " + instructorDetail.getId() + " not found");
        }
        instructorDetailDao.update(instructorDetail);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        InstructorDetail instructorDetail = instructorDetailDao.findInstructorDetailById(id);
        if (instructorDetail == null) {
            throw new ResourceNotFoundException("InstructorDetail with id " + id + " not found");
        }
        return instructorDetail;
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = instructorDetailDao.findInstructorDetailById(id);
        if (instructorDetail == null) {
            throw new ResourceNotFoundException("InstructorDetail with id " + id + " not found");
        }
        instructorDetailDao.deleteInstructorDetailById(id);
    }
}
