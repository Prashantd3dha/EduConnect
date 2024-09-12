package com.prashant.EduConnect.student.dao;


import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao{
    private final EntityManager entityManager;

    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findStudentById(int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        return student;
    }

    @Override
    @Transactional
    public void addCourse(int studentId, Course course) {
        Student student = entityManager.find(Student.class, studentId);
        Course course1 = entityManager.find(Course.class, course.getId());
        course1.addStudent(student);

        entityManager.merge(course1);
    }

    @Override
    @Transactional
    public void deleteStudentById(int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}
