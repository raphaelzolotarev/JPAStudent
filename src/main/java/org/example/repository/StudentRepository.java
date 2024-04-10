package org.example.repository;

import org.example.model.Student;

import javax.persistence.*;
import java.util.List;

public class StudentRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;
    public StudentRepository(){
        this.emf = Persistence.createEntityManagerFactory("database-configuration");
        this.entityManager = this.emf.createEntityManager();
    }

    public Student addStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }
    public Student find(Long id){
        return entityManager.find(Student.class, id);
    }
    public Student findById(Long id){
        Query query = entityManager.createNamedQuery("find student by id");
        query.setParameter("id", id);
        return (Student) query.getSingleResult();
    }
    public Student update(Student student){
        Student studentToUpdate = find(student.getId());
        entityManager.getTransaction().begin();
        studentToUpdate.setFirstname(student.getFirstname());
        studentToUpdate.setLastname(student.getLastname());
        //entityManager.merge(student);
        entityManager.getTransaction().commit();
        return studentToUpdate;
    }
    public void delete(Student student){
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }

    public List<String> findFirstNames(){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select s.firstname from Student s");
        entityManager.getTransaction().commit();
        return query.getResultList();
    }
    public List<String> findLastNames(){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select s.lastname from Student s");
        entityManager.getTransaction().commit();
        return query.getResultList();
    }

    public void closeDb(){
        emf.close();
        entityManager.close();
    }


}
