package org.example.repository;

import org.example.model.School;
import org.example.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TutorRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TutorRepository(){
        this.emf = Persistence.createEntityManagerFactory("database-configuration");
        entityManager = emf.createEntityManager();
    }


    //ADD
    public Tutor add(Tutor school){
        entityManager.getTransaction().begin();
        entityManager.persist(school);
        entityManager.getTransaction().commit();
        entityManager.clear();
        return school;
    }

    //FIND
    public Tutor find(Long id){
        entityManager.clear();return entityManager.find(Tutor.class, id);
    }

    //UPDATE
    public Tutor update(Tutor tutor){
        Tutor tutorToUpdate = find(tutor.getId());
        entityManager.getTransaction().begin();
        tutorToUpdate.setFirstname(tutor.getFirstname());
        tutorToUpdate.setLastname(tutor.getLastname());
        entityManager.getTransaction().commit();
        entityManager.clear();
        return tutorToUpdate;
    }

    //DELETE
    public void delete(Tutor tutor){
        entityManager.getTransaction().begin();
        entityManager.remove(tutor);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    //CLOSE CONNECTION
    public void closeConnection(){
        entityManager.close();
        emf.close();
    }


}
