package com.prashant.EduConnect.course.service;


import com.prashant.EduConnect.course.dao.CourseDao;
import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.course.entity.Review;
import com.prashant.EduConnect.exception.ResourceNotFoundException;
import com.prashant.EduConnect.student.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    @Transactional
    public void save(Course course) {
        if (course == null || course.getTitle() == null || course.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Course title cannot be null or empty");
        }
        courseDao.save(course);
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = courseDao.findAll();
        if (courses == null || courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found");
        }
        return courses;
    }

    @Override
    @Transactional
    public void addStudent(Student student, int courseId) {
        Course course = courseDao.findCourseById(courseId);
        if (course == null) {
            throw new ResourceNotFoundException("Course with id " + courseId + " not found");
        }
        courseDao.addStudent(student, courseId);
    }

    @Override
    @Transactional
    public void addReview(Review review, int courseId) {
        Course course = courseDao.findCourseById(courseId);
        if (course == null) {
            throw new ResourceNotFoundException("Course with id " + courseId + " not found");
        }
        courseDao.addReview(review, courseId);
    }

    @Override
    public List<Course> findCoursesByStudentId(int studentId) {
        List<Course> courses = courseDao.findCoursesByStudentId(studentId);
        if (courses == null || courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found for student id: "+studentId);
        }
        return courses;
    }


    @Override
    public Course findCourseById(int courseId) {
        Course course = courseDao.findCourseById(courseId);
        if (course == null) {
            throw new ResourceNotFoundException("Course with id " + courseId + " not found");
        }
        return course;
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        List<Course> courses = courseDao.findCoursesByInstructorId(instructorId);
        if (courses == null || courses.isEmpty()) {
            throw new ResourceNotFoundException("No courses found for instructor with id " + instructorId);
        }
        return courses;
    }

    @Override
    @Transactional
    public void deleteCourseById(int courseId) {
        Course course = courseDao.findCourseById(courseId);
        if (course == null) {
            throw new ResourceNotFoundException("Course with id " + courseId + " not found");
        }
        courseDao.deleteCourseById(courseId);
    }

    @Override
    @Transactional
    public void update(Course course) {
        if (course == null || course.getTitle() == null || course.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Course title cannot be null or empty");
        }
        if (courseDao.findCourseById(course.getId()) == null) {
            throw new ResourceNotFoundException("Course with id " + course.getId() + " not found");
        }
        courseDao.update(course);
    }
}
