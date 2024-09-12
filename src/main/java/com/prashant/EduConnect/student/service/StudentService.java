package com.prashant.EduConnect.student.service;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.student.entity.Student;

import java.util.List;

public interface StudentService {
    void save(Student student);
    List<Student> findAll();
    Student findStudentById(int id);
    void addCourse(int studentId, Course course);
    void deleteStudentById(int id);
    void update(Student student);
}
