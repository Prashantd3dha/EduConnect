package com.prashant.EduConnect.course.dao;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.course.entity.Review;
import com.prashant.EduConnect.student.entity.Student;

import java.util.List;

public interface CourseDao {
    void save(Course course);
    void addStudent(Student student, int courseId);
    void addReview(Review review, int courseId);
    List<Course> findAll();
    Course findCourseById(int courseId);
    List<Course> findCoursesByInstructorId(int instructorId);
    List<Course> findCoursesByStudentId(int studentId);
    void deleteCourseById(int courseId);
    void update(Course course);
}
