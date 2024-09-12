package com.prashant.EduConnect.instructor.rest;

import com.prashant.EduConnect.instructor.entity.InstructorDetail;
import com.prashant.EduConnect.instructor.service.InstructorDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructor-details")
public class InstructorDetailController {
    private final InstructorDetailService instructorDetailService;

    @Autowired
    public InstructorDetailController(InstructorDetailService instructorDetailService) {
        this.instructorDetailService = instructorDetailService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorDetail> getInstructorDetailById(@PathVariable int id) {
        InstructorDetail instructorDetail = instructorDetailService.findInstructorDetailById(id);
        return new ResponseEntity<>(instructorDetail, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInstructorDetail(@RequestBody InstructorDetail instructorDetail, @PathVariable int id) {
        instructorDetail.setId(id);  // Ensure the ID is set to the one provided in the path
        instructorDetailService.update(instructorDetail);
        return new ResponseEntity<>("Instructor detail updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructorDetailById(@PathVariable int id) {
        instructorDetailService.deleteInstructorDetailById(id);
        return new ResponseEntity<>("Instructor detail deleted successfully", HttpStatus.OK);
    }
}
