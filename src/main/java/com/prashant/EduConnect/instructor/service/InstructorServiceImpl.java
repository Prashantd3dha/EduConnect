package com.prashant.EduConnect.instructor.service;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.exception.ResourceNotFoundException;
import com.prashant.EduConnect.instructor.dao.InstructorDao;
import com.prashant.EduConnect.instructor.entity.Instructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService{
    private final InstructorDao instructorDao;

    public InstructorServiceImpl(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        instructorDao.save(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        Instructor instructor = instructorDao.findInstructorById(id);
        if (instructor == null) {
            throw new ResourceNotFoundException("Instructor with id " + id + " not found");
        }
        return instructor;
    }

    @Override
    public List<Instructor> findAll() {
        return instructorDao.findAll();
    }

    @Override
    @Transactional
    public void addCourse(Course course, int instructorId) {
        Instructor instructor = instructorDao.findInstructorById(instructorId);
        if (instructor == null) {
            throw new ResourceNotFoundException("Instructor with id " + instructorId + " not found");
        }
        instructorDao.addCourse(course, instructorId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = instructorDao.findInstructorById(id);
        if (instructor == null) {
            throw new ResourceNotFoundException("Instructor with id " + id + " not found");
        }
        instructorDao.deleteInstructorById(id);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        if (instructorDao.findInstructorById(instructor.getId()) == null) {
            throw new ResourceNotFoundException("Instructor with id " + instructor.getId() + " not found");
        }
        instructorDao.update(instructor);
    }
}
