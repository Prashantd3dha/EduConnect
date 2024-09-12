package com.prashant.EduConnect.course.dao;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.course.entity.Review;
import com.prashant.EduConnect.student.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao{
    private final EntityManager entityManager;

    public CourseDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    @Transactional
    public void addStudent(Student student, int courseId) {
        Course course = entityManager.find(Course.class, courseId);
        Student student1 = entityManager.find(Student.class, student.getId());
        course.addStudent(student1);
        entityManager.merge(course);
    }

    @Override
    public void addReview(Review review, int courseId) {
        Course course = entityManager.find(Course.class, courseId);
        review.setCourse(course);
        course.addReview(review);
        entityManager.merge(course);
    }

    @Override
    public List<Course> findAll() {
        TypedQuery<Course> query = entityManager.createQuery("FROM Course", Course.class);
        return query.getResultList();
    }

    @Override
    public Course findCourseById(int courseId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c WHERE c.id = :courseId", Course.class);
        query.setParameter("courseId", courseId);
        return query.getSingleResult();
    }

    @Override
    public List<Course> findCoursesByInstructorId(int instructorId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :instructorId", Course.class);
        query.setParameter("instructorId", instructorId);
        return query.getResultList();
    }

    @Override
    public List<Course> findCoursesByStudentId(int studentId) {
        TypedQuery<Course> query = entityManager.createQuery("Select c from Course c JOIN FETCH c.students s where s.id = :studentId", Course.class);
        query.setParameter("studentId", studentId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteCourseById(int courseId) {
        Course course = entityManager.find(Course.class, courseId);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void update(Course course) {
        Course existingCourse = entityManager.find(Course.class, course.getId());
        existingCourse.setTitle(course.getTitle());
        entityManager.merge(existingCourse);
    }
}
