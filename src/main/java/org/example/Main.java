package org.example;

import org.example.model.School;
import org.example.model.Student;
import org.example.model.Tutor;
import org.example.repository.SchoolRepository;
import org.example.repository.StudentRepository;
import org.example.repository.TutorRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();
        SchoolRepository schoolRepository = new SchoolRepository();
        TutorRepository tutorRepository = new TutorRepository();

        System.out.println("\n #1");
        Student student = new Student("Alain", "Red");
        studentRepository.addStudent(student);
        System.out.println("Student added "+student.toString());

        System.out.println("\n #2");
        studentRepository.findFirstNames().forEach(System.out::println);
        studentRepository.findLastNames().forEach(System.out::println);

        System.out.println("\n #3");
        student = studentRepository.find(student.getId());
        System.out.println("Student found "+student.toString());

        System.out.println("\n #4");
        student = studentRepository.findById(student.getId());
        System.out.println("Student found (JPQL) "+student.toString());

        System.out.println("\n #5");
        student.setLastname("Green");
        studentRepository.update(student);
        System.out.println("Student updated "+student.toString());

        System.out.println("\n #6");
        studentRepository.updateFirstNameById("Fred", student.getId());
        System.out.println("updated first name (JPQL) "+student.toString());

        System.out.println("\n #7");
        studentRepository.updateLastNameById("Yellow", student.getId());
        System.out.println("updated last name (JPQL) "+student.toString());

        /*
        System.out.println("\n #8");
        studentRepository.delete(student);
        System.out.println("Student deleted "+student.toString());
        */

        System.out.println("\n #9");
        List<Student> students = studentRepository.findFirstNamesStartWith("Fr");
        students.forEach(System.out::println);

        System.out.println("\n #10");
        List<Student> students2 = studentRepository.findLastNamesEndWith("ow");
        students2.forEach(System.out::println);

        System.out.println("\n #11");
        System.out.println("Number of students: "+studentRepository.count());

        System.out.println("\n #12");
        studentRepository.findSortingByFirstName().forEach(System.out::println);

        System.out.println("\n #13");
        studentRepository.findSortingById().forEach(System.out::println);

        System.out.println("\n #14");
        School school = new School("Harvard", "NYC");
        schoolRepository.add(school);
        System.out.println("School added "+ school.toString());
        studentRepository.addSchool(student.getId(), school);
        System.out.println("Student with school "+ student.toString());
        student=studentRepository.find(student.getId());
        System.out.println("Found student with school: "+student);

        System.out.println("\n #15");
        Tutor tutor = new Tutor("Jon", "Bates");
        tutorRepository.add(tutor);
        System.out.println("Tutor added "+ tutor.toString());
        studentRepository.addTutor(student.getId(), tutor);
        System.out.println("Student with tutor "+ student.toString());
        student=studentRepository.find(student.getId());
        System.out.println("Found student with tutor: "+student);

        System.out.println("\n #16");
        School school2 = new School("INTEC", "Brussel");
        schoolRepository.add(school2);
        System.out.println("School added "+school2);
        schoolRepository.addStudent(school.getId(), student);
        school2=schoolRepository.find(school2.getId());
        school2.getStudents().forEach(System.out::println);

        System.out.println("\n #17");
        schoolRepository.removeStudent(student.getId(), student);
        school=schoolRepository.find(school.getId());
        school.getStudents().forEach(System.out::println);





    }
}