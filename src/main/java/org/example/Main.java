package org.example;

import org.example.model.Student;
import org.example.repository.StudentRepository;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Alain", "Red");
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.addStudent(student);
        System.out.println("Student added "+student.toString());

        student = studentRepository.find(student.getId());
        System.out.println("Student found "+student.toString());

        student.setLastname("Demir");
        studentRepository.update(student);


    }
}