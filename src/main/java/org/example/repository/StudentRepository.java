package org.example.repository;

import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class StudentRepository {
   private EntityManager entityManager;
   private EntityManagerFactory emf;
    public StudentRepository(){
        this.emf = Persistence.createEntityManagerFactory("database-configuration");
        this.entityManager = this.emf.createEntityManager();
    }
    public StudentRepository(String pu){
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    public Student addStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        return student;
    }
    public void delete(Student student){
        entityManager.getTransaction().begin();
        entityManager.remove(student);
        entityManager.getTransaction().commit();
    }
    public Student find(Long id){
        return entityManager.find(Student.class, id);
    }
    public void update(Student student){
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }

    public void closeDb(){
        emf.close();
        entityManager.close();
    }


}
