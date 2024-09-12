package com.prashant.EduConnect.student.dao;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.student.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student student);
    List<Student> findAll();
    Student findStudentById(int studentId);
    void addCourse(int studentId, Course course);
    void deleteStudentById(int studentId);
    void update(Student student);
}
