package com.prashant.EduConnect.instructor.dao;

import com.prashant.EduConnect.course.entity.Course;
import com.prashant.EduConnect.instructor.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class InstructorDaoImpl implements InstructorDao{
    private final EntityManager entityManager;

    public InstructorDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public List<Instructor> findAll() {
        TypedQuery<Instructor> query = entityManager.createQuery("FROM Instructor", Instructor.class);
        List<Instructor> instructors = query.getResultList();

        return instructors;
    }

    @Override
    @Transactional
    public void addCourse(Course course, int instructorId) {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        Course course1 = entityManager.find(Course.class, course.getId());
        instructor.add(course1);
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        if (instructor != null) {
            // Unlink courses associated with the instructor
            instructor.getCourses().forEach(course -> course.setInstructor(null));
            entityManager.remove(instructor);
        }
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        Instructor existingInstructor = entityManager.find(Instructor.class, instructor.getId());
        existingInstructor.setFirstName(instructor.getFirstName());
        existingInstructor.setLastName(instructor.getLastName());
        existingInstructor.setEmail(instructor.getEmail());
        existingInstructor.getInstructorDetail().setYoutubeChannel(instructor.getInstructorDetail().getYoutubeChannel());
        existingInstructor.getInstructorDetail().setHobby(instructor.getInstructorDetail().getHobby());
        entityManager.merge(existingInstructor);
    }
}
