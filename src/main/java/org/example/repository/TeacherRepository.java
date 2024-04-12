package org.example.repository;

import org.example.model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TeacherRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TeacherRepository(){
        this.emf = Persistence.createEntityManagerFactory("database-configuration");
        entityManager = emf.createEntityManager();
    }


    //ADD
    public Teacher add(Teacher teacher){
        entityManager.getTransaction().begin();
        entityManager.merge(teacher);
        entityManager.getTransaction().commit();
        entityManager.clear();
        return teacher;
    }

    //FIND
    public Teacher find(Long id){
        entityManager.clear();
        return entityManager.find(Teacher.class, id);
    }

    //UPDATE
    public Teacher update(Teacher teacher){
        Teacher teacherToUpdate = find(teacher.getId());
        entityManager.getTransaction().begin();
        teacherToUpdate.setFirstname(teacher.getFirstname());
        teacherToUpdate.setLastname(teacher.getLastname());
        entityManager.getTransaction().commit();
        entityManager.clear();
        return teacherToUpdate;
    }

    //DELETE
    public void delete(Teacher teacher){
        entityManager.getTransaction().begin();
        entityManager.remove(teacher);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    //CLOSE CONNECTION
    public void closeConnection(){
        entityManager.close();
        emf.close();
    }


}
