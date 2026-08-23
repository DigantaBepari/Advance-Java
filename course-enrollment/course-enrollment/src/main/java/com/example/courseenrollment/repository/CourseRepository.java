package com.example.courseenrollment.repository;

import com.example.courseenrollment.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {

    private static final List<Course> courses = new ArrayList<>(List.of(
            new Course(1L, "CSE101", "Introduction to Programming", 3, "Dr. Karim", 30),
            new Course(2L, "CSE201", "Data Structures", 3, "Dr. Hasan", 25),
            new Course(3L, "CSE301", "Database Systems", 4, "Dr. Rahman", 20),
            new Course(4L, "EEE201", "Digital Electronics", 3, "Dr. Ahmed", 30)
    ));

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    public Optional<Course> findByCode(String code) {
        return courses.stream()
                .filter(course -> course.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    public Course save(Course course) {
        courses.add(course);
        return course;
    }

    public long nextId() {
        return courses.stream()
                .mapToLong(Course::getId)
                .max()
                .orElse(0) + 1;
    }
}
