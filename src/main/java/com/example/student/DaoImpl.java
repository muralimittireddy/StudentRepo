package com.example.student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoImpl implements DAO{
    private EntityManager entityManager;

    @Autowired
    public DaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
        System.out.println("hello");
    }

    public List<Student> getStudentByRoll(Integer roll)
    {
        String jpql = "select s FROM Student s WHERE s.roll = :a";


        TypedQuery<Student> query = entityManager.createQuery(jpql,Student.class);
        query.setParameter("a", roll);// Bind a value to the :name parameter
        //System.out.println("query executed");

        List<Student> students = query.getResultList(); // Execute the query and get a list of Student objects
        //System.out.println("returning students");
        return students;

    }

    @Override
    @Transactional
    public void updateStudentByName(Student s) {
        entityManager.merge(s);
    }

    @Override

    public List<Student> getStudentByName(String name) {
        String jpql = "select s FROM Student s WHERE s.name = :a";


        TypedQuery<Student> query = entityManager.createQuery(jpql,Student.class);
        query.setParameter("a", name);// Bind a value to the :name parameter
        //System.out.println("query executed");

        List<Student> students = query.getResultList(); // Execute the query and get a list of Student objects
        //System.out.println("returning students");
        return students;
    }

    @Override
    @Transactional
    public void deleteStudent(Student s) {
        entityManager.remove(s);
    }
}
