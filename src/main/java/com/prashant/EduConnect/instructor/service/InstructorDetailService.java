package com.prashant.EduConnect.instructor.service;

import com.prashant.EduConnect.instructor.entity.InstructorDetail;

public interface InstructorDetailService {
    void update(InstructorDetail instructorDetail);
    InstructorDetail findInstructorDetailById(int id);
    void deleteInstructorDetailById(int id);
}
