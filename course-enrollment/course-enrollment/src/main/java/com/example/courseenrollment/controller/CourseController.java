package com.example.courseenrollment.controller;

import com.example.courseenrollment.entity.Course;
import com.example.courseenrollment.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(courseService.createCourse(course));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(
            @RequestParam(required = false) Integer minCredit) {
        return ResponseEntity.ok(courseService.getCourses(minCredit));
    }
}
