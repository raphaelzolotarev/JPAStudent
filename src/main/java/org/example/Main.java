package org.example;

import org.example.model.Student;
import org.example.repository.StudentRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Alain", "Red");

        StudentRepository studentRepository = new StudentRepository();

        studentRepository.addStudent(student);
        System.out.println("Student added "+student.toString());

        studentRepository.findFirstNames().forEach(System.out::println);
        studentRepository.findLastNames().forEach(System.out::println);


        student = studentRepository.find(student.getId());
        System.out.println("Student found "+student.toString());

        student.setLastname("Green");
        studentRepository.update(student);
        System.out.println("Student updated "+student.toString());

        studentRepository.delete(student);
        System.out.println("Student deleted "+student.toString());


    }
}