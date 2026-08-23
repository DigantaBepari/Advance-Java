package com.example.courseenrollment.repository;

import com.example.courseenrollment.entity.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {

    private static final List<Enrollment> enrollments = new ArrayList<>(List.of(
            new Enrollment(1L, 1L, 1L, "Spring 2025", 3.50),
            new Enrollment(2L, 1L, 2L, "Spring 2025", 3.00),
            new Enrollment(3L, 2L, 1L, "Spring 2025", 3.75),
            new Enrollment(4L, 2L, 3L, "Fall 2025", 3.25),
            new Enrollment(5L, 3L, 4L, "Spring 2025", 2.50),
            new Enrollment(6L, 4L, 1L, "Fall 2025", 3.00),
            new Enrollment(7L, 5L, 2L, "Spring 2025", 3.80),
            new Enrollment(8L, 5L, 3L, "Fall 2025", null)
    ));

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Optional<Enrollment> findById(Long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst();
    }

    public Enrollment save(Enrollment enrollment) {
        enrollments.add(enrollment);
        return enrollment;
    }

    public long nextId() {
        return enrollments.stream()
                .mapToLong(Enrollment::getId)
                .max()
                .orElse(0) + 1;
    }
}
