package org.example.repository;

import org.example.model.School;
import org.example.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SchoolRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public SchoolRepository(){
        this.emf = Persistence.createEntityManagerFactory("database-configuration");
        this.entityManager = this.emf.createEntityManager();
    }


    //ADD
    public School add(School school){
        entityManager.getTransaction().begin();
        entityManager.persist(school);
        entityManager.getTransaction().commit();
        return school;
    }
    public void addStudent(long id, Student student){
        entityManager.getTransaction().begin();
        School school = find(id);
        if(school != null){
            school.getStudents().add(student);
        }
        entityManager.persist(school);
        entityManager.getTransaction().commit();
    }

    //FIND
    public School find(Long id){
        return entityManager.find(School.class, id);
    }

    //UPDATE
    public School update(School school){
        School schoolToUpdate = find(school.getId());
        entityManager.getTransaction().begin();
        schoolToUpdate.setName(school.getName());
        schoolToUpdate.setCity(school.getCity());
        entityManager.getTransaction().commit();
        return schoolToUpdate;
    }

    //DELETE
    public void delete(School school){
        entityManager.getTransaction().begin();
        entityManager.remove(school);
        entityManager.getTransaction().commit();
    }
    public void removeStudent(long id, Student student){
        entityManager.getTransaction().begin();
        School school = find(id);
        if(school != null){
            school.getStudents().remove(student);
        }
        entityManager.persist(school);
        entityManager.getTransaction().commit();
    }

    //CLOSE CONNECTION
    public void closeDb(){
        emf.close();
        entityManager.close();
    }


}
