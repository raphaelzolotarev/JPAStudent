package org.example.repository;

import org.example.model.School;
import org.example.model.Student;
import org.example.model.Tutor;

import javax.persistence.*;
import java.util.List;

public class StudentRepository {
    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public StudentRepository(){
        this.emf = Persistence.createEntityManagerFactory("database-configuration");
        entityManager = emf.createEntityManager();
    }


    //ADD
    public Student addStudent(Student student){
        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.clear();
        return student;
    }
    public Student addSchool(long id, School school){
        entityManager.getTransaction().begin();
        Student student = find(id);
        student.setSchool(school);
        entityManager.getTransaction().commit();
        entityManager.clear();
        return student;
    }
    public Student addTutor(long id, Tutor tutor){
        entityManager.getTransaction().begin();
        Student student = find(id);
        student.setTutor(tutor);
        entityManager.getTransaction().commit();
        entityManager.clear();
        return student;
    }


    //FIND
    public Student find(Long id){
        entityManager.clear();
        return entityManager.find(Student.class, id);

    }
    public Student findById(Long id){
        Query query = entityManager.createNamedQuery("find student by id");
        query.setParameter("id", id);
        entityManager.clear();
        return (Student) query.getSingleResult();
    }
    public List<String> findFirstNames(){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select s.firstname from Student s");
        entityManager.getTransaction().commit();
        entityManager.clear();
        return query.getResultList();
    }
    public List<String> findLastNames(){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Select s.lastname from Student s");
        entityManager.getTransaction().commit();
        entityManager.clear();
        return query.getResultList();
    }
    //FIND FILTER
    public List<Student> findFirstNamesStartWith(String word){
        Query query = entityManager.createQuery("Select s from Student s where firstname like '"+word+"%'");
        entityManager.clear();
        return query.getResultList();
    }
    public List<Student> findLastNamesEndWith(String word){
        Query query = entityManager.createQuery("Select s from Student s where lastname like '%"+word+"'");
        entityManager.clear();
        return query.getResultList();
    }
    //FIND SORT
    public List<Student> findSortingByFirstName(){
        Query query = entityManager.createQuery("Select s from Student s order by firstname desc");
        entityManager.clear();
        return query.getResultList();
    }
    public List<Student> findSortingById(){
        Query query = entityManager.createQuery("Select s from Student s order by id desc");
        entityManager.clear();
        return query.getResultList();
    }

    //UPDATE
    public Student update(Student student){
        Student studentToUpdate = find(student.getId());
        entityManager.getTransaction().begin();
        studentToUpdate.setFirstname(student.getFirstname());
        studentToUpdate.setLastname(student.getLastname());
        //entityManager.merge(student);
        entityManager.getTransaction().commit();
        entityManager.clear();
        return studentToUpdate;
    }
    public Student updateFirstNameById(String firstName, long id){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Update Student set firstname='"+firstName+"' where id = "+id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return findById(id);
    }
    public Student updateLastNameById(String lastName, long id){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Update Student set lastname='"+lastName+"' where id = "+id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
        return findById(id);
    }


    //DELETE
    public void delete(Student student){
        entityManager.getTransaction().begin();
        Student attachedStudent = entityManager.merge(student);
        entityManager.remove(attachedStudent);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }
    public void deleteById(long id){
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("Delete from Student where id="+id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.clear();
    }


    //COUNT
    public Long count(){
        Query query = entityManager.createQuery("Select count(s) from Student s");
        entityManager.clear();
        return (Long)query.getSingleResult();
    }


    //CLOSE CONNECTION
    public void closeConnection(){
        entityManager.close();
        emf.close();
    }


}
