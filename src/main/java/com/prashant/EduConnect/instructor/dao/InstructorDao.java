package com.prashant.EduConnect.instructor.dao;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.instructor.entity.Instructor;

import java.util.List;

public interface InstructorDao {
    void save(Instructor instructor);
    Instructor findInstructorById(int id);
    List<Instructor> findAll();
    void addCourse(Course course, int instructorId);
    void deleteInstructorById(int id);
    void update(Instructor instructor);
}
