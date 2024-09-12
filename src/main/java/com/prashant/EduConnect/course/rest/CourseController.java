package com.prashant.EduConnect.course.rest;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.course.entity.Review;
import com.prashant.EduConnect.course.service.CourseService;
import com.prashant.EduConnect.student.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<String> saveCourse(@RequestBody Course course) {
        courseService.save(course);
        return new ResponseEntity<>("Course saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> findCourseById(@PathVariable int courseId) {
        Course course = courseService.findCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAllCourses(){
        List<Course> courses = courseService.findAll();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PatchMapping("/{courseId}/students")
    public ResponseEntity<String> addStudentToCourse(@RequestBody Student student, @PathVariable int courseId) {
        courseService.addStudent(student, courseId);
        return new ResponseEntity<>("Student added to course successfully", HttpStatus.OK);
    }

    @PostMapping("/{courseId}/reviews")
    public ResponseEntity<String> addReviewToCourse(@RequestBody Review review, @PathVariable int courseId){
        courseService.addReview(review, courseId);
        return new ResponseEntity<>("Review added to course successfully", HttpStatus.OK);
    }

    @GetMapping("/instructors/{instructorId}")
    public ResponseEntity<List<Course>> findCoursesByInstructorId(@PathVariable int instructorId) {
        List<Course> courses = courseService.findCoursesByInstructorId(instructorId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}")
    public  ResponseEntity<List<Course>> findCoursesByStudentId(@PathVariable int studentId){
        List<Course> courses = courseService.findCoursesByStudentId(studentId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable int courseId) {
        courseService.deleteCourseById(courseId);
        return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("/{courseId}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable int courseId) {
        course.setId(courseId);
        courseService.update(course);
        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
    }
}
