package com.prashant.EduConnect.course.service;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.course.entity.Review;
import com.prashant.EduConnect.student.entity.Student;

import java.util.List;

public interface CourseService {
    void save(Course course);
    Course findCourseById(int courseId);
    List<Course> findAll();
    void addStudent(Student student, int courseId);
    void addReview(Review review, int courseId);
    List<Course> findCoursesByStudentId(int studentId);
    List<Course> findCoursesByInstructorId(int instructorId);
    void deleteCourseById(int courseId);
    void update(Course course);
}
