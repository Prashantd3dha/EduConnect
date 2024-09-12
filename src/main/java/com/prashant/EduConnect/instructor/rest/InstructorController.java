package com.prashant.EduConnect.instructor.rest;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.instructor.entity.Instructor;
import com.prashant.EduConnect.instructor.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping
    public ResponseEntity<String> saveInstructor(@RequestBody Instructor instructor) {
        instructorService.save(instructor);
        return new ResponseEntity<>("Instructor created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable int id) {
        Instructor instructor = instructorService.findInstructorById(id);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.findAll();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }

    @PostMapping("/{instructorId}/courses")
    public ResponseEntity<String> addCourseToInstructor(@RequestBody Course course, @PathVariable int instructorId) {
        instructorService.addCourse(course, instructorId);
        return new ResponseEntity<>("Course added successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable int id) {
        instructorService.deleteInstructorById(id);
        return new ResponseEntity<>("Instructor deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInstructor(@RequestBody Instructor instructor, @PathVariable int id) {
        instructor.setId(id);
        instructorService.update(instructor);
        return new ResponseEntity<>("Instructor updated successfully", HttpStatus.OK);
    }
}
