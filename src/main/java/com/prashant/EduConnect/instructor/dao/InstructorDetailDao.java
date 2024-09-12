package com.prashant.EduConnect.instructor.dao;

import com.prashant.EduConnect.instructor.entity.InstructorDetail;

public interface InstructorDetailDao {
    void update(InstructorDetail instructorDetail); //
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
