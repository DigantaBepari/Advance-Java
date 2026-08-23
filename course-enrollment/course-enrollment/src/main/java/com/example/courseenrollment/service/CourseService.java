package com.example.courseenrollment.service;

import com.example.courseenrollment.entity.Course;
import com.example.courseenrollment.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        if (courseRepository.findByCode(course.getCode()).isPresent()) {
            throw new IllegalArgumentException("Course code already exists");
        }

        course.setId(courseRepository.nextId());
        return courseRepository.save(course);
    }

    public List<Course> getCourses(Integer minCredit) {
        if (minCredit == null) {
            return courseRepository.findAll();
        }

        return courseRepository.findAll().stream()
                .filter(course -> course.getCredit() >= minCredit)
                .toList();
    }

    public Course getCourse(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
