package com.prashant.EduConnect.student.rest;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.student.entity.Student;
import com.prashant.EduConnect.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        studentService.save(student);
        return new ResponseEntity<>("Student saved successfully", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findStudentById(@PathVariable int studentId) {
        Student student = studentService.findStudentById(studentId);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PatchMapping("/{studentId}")
    public ResponseEntity<String> updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        student.setId(studentId);
        studentService.update(student);
        return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentId) {
        studentService.deleteStudentById(studentId);
        return new ResponseEntity<>("Student deleted with id: "+studentId,HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{studentId}/courses")
    public ResponseEntity<String> addCourseToStudent (@RequestBody Course course, @PathVariable int studentId){
        studentService.addCourse(studentId,course);
        return new ResponseEntity<>("Course added succesfully", HttpStatus.OK);
    }
}
