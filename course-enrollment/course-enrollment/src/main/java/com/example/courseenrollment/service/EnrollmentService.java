package com.example.courseenrollment.service;

import com.example.courseenrollment.entity.Course;
import com.example.courseenrollment.entity.Enrollment;
import com.example.courseenrollment.entity.Student;
import com.example.courseenrollment.repository.CourseRepository;
import com.example.courseenrollment.repository.EnrollmentRepository;
import com.example.courseenrollment.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        Student student = studentRepository.findById(enrollment.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(enrollment.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        boolean alreadyEnrolled = enrollmentRepository.findAll().stream()
                .anyMatch(e ->
                        e.getStudentId().equals(student.getId()) &&
                                e.getCourseId().equals(course.getId()) &&
                                e.getSemester().equalsIgnoreCase(enrollment.getSemester())
                );

        if (alreadyEnrolled) {
            throw new IllegalArgumentException("Student already enrolled in this course for this semester");
        }

        long seatsFilled = enrollmentRepository.findAll().stream()
                .filter(e -> e.getCourseId().equals(course.getId()))
                .count();

        if (seatsFilled >= course.getCapacity()) {
            throw new IllegalArgumentException("Course capacity exceeded");
        }

        enrollment.setId(enrollmentRepository.nextId());

        return enrollmentRepository.save(enrollment);
    }

    public Enrollment updateGrade(Long id, Double grade) {
        if (grade != null && (grade < 0.00 || grade > 4.00)) {
            throw new IllegalArgumentException("Grade must be between 0.00 and 4.00");
        }

        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setGrade(grade);

        return enrollment;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollment(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }
}
