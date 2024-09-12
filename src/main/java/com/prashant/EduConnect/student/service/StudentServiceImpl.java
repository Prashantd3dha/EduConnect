package com.prashant.EduConnect.student.service;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.exception.ResourceNotFoundException;
import com.prashant.EduConnect.student.dao.StudentDao;
import com.prashant.EduConnect.student.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    private final StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentDao.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student findStudentById(int id) {
        Student student = studentDao.findStudentById(id);
        if (student == null) {
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        return student;
    }

    @Override
    @Transactional
    public void addCourse(int studentId, Course course) {
        Student student = studentDao.findStudentById(studentId);
        if (student == null) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }
        studentDao.addCourse(studentId,course);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = studentDao.findStudentById(id);
        if (student == null) {
            throw new ResourceNotFoundException("Cannot delete. Student not found with id: " + id);
        }
        studentDao.deleteStudentById(id);
    }

    @Override
    @Transactional
    public void update(Student student) {
        Student existingStudent = studentDao.findStudentById(student.getId());
        if (existingStudent == null) {
            throw new ResourceNotFoundException("Cannot update. Student not found with id: " + student.getId());
        }
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        studentDao.update(existingStudent);
    }
}
