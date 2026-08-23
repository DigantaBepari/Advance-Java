package com.example.courseenrollment.repository;

import com.example.courseenrollment.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepository {

    private static final List<Student> students = new ArrayList<>(List.of(
            new Student(1L, "Rahim Ahmed", "rahim@gmail.com", "CSE", 2022),
            new Student(2L, "Nusrat Jahan", "nusrat@gmail.com", "CSE", 2023),
            new Student(3L, "Tanvir Hasan", "tanvir@gmail.com", "EEE", 2022),
            new Student(4L, "Sadia Islam", "sadia@gmail.com", "BBA", 2024),
            new Student(5L, "Fahim Khan", "fahim@gmail.com", "CSE", 2021)
    ));

    public List<Student> findAll() {
        return students;
    }

    public Optional<Student> findById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    public Optional<Student> findByEmail(String email) {
        return students.stream()
                .filter(student -> student.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    public Student save(Student student) {
        students.add(student);
        return student;
    }

    public void delete(Student student) {
        students.remove(student);
    }

    public long nextId() {
        return students.stream()
                .mapToLong(Student::getId)
                .max()
                .orElse(0) + 1;
    }
}
