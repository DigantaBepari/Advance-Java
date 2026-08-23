package com.example.courseenrollment.service;

import com.example.courseenrollment.dto.CourseRosterDto;
import com.example.courseenrollment.dto.DepartmentSummaryDto;
import com.example.courseenrollment.dto.TopPerformerDto;
import com.example.courseenrollment.dto.TranscriptDto;
import com.example.courseenrollment.entity.Course;
import com.example.courseenrollment.entity.Enrollment;
import com.example.courseenrollment.entity.Student;
import com.example.courseenrollment.repository.CourseRepository;
import com.example.courseenrollment.repository.EnrollmentRepository;
import com.example.courseenrollment.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ReportService(StudentRepository studentRepository,
                         CourseRepository courseRepository,
                         EnrollmentRepository enrollmentRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public TranscriptDto getTranscript(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Enrollment> enrollments = enrollmentRepository.findAll().stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .toList();

        List<TranscriptDto.CourseResult> courses = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            Course course = courseRepository.findById(enrollment.getCourseId())
                    .orElse(null);

            if (course != null) {
                courses.add(new TranscriptDto.CourseResult(
                        course.getCode(),
                        course.getTitle(),
                        course.getCredit(),
                        enrollment.getSemester(),
                        enrollment.getGrade()
                ));
            }
        }

        int totalCredits = 0;
        double totalPoints = 0;
        int gradedCourses = 0;

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getGrade() != null) {
                Course course = courseRepository.findById(enrollment.getCourseId())
                        .orElse(null);

                if (course != null) {
                    if (enrollment.getGrade() >= 2.00) {
                        totalCredits += course.getCredit();
                    }

                    totalPoints += enrollment.getGrade() * course.getCredit();
                    gradedCourses += course.getCredit();
                }
            }
        }

        double cgpa = gradedCourses == 0 ? 0.0 : totalPoints / gradedCourses;

        return new TranscriptDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDepartment(),
                student.getAdmissionYear(),
                courses,
                totalCredits,
                round(cgpa)
        );
    }

    public CourseRosterDto getCourseRoster(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        List<Enrollment> enrollments = enrollmentRepository.findAll().stream()
                .filter(e -> e.getCourseId().equals(courseId))
                .toList();

        List<CourseRosterDto.StudentInfo> students = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            Student student = studentRepository.findById(enrollment.getStudentId())
                    .orElse(null);

            if (student != null) {
                students.add(new CourseRosterDto.StudentInfo(
                        student.getId(),
                        student.getName(),
                        student.getEmail(),
                        student.getDepartment()
                ));
            }
        }

        List<Double> grades = enrollments.stream()
                .map(Enrollment::getGrade)
                .filter(g -> g != null)
                .toList();

        double averageGrade = grades.isEmpty()
                ? 0.0
                : grades.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

        int seatsFilled = enrollments.size();
        int seatsRemaining = Math.max(0, course.getCapacity() - seatsFilled);

        return new CourseRosterDto(
                course.getId(),
                course.getCode(),
                course.getTitle(),
                course.getCredit(),
                course.getInstructor(),
                course.getCapacity(),
                students,
                seatsFilled,
                seatsRemaining,
                round(averageGrade)
        );
    }

    public List<DepartmentSummaryDto> getDepartmentSummary() {
        List<Student> students = studentRepository.findAll();
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        Map<String, List<Student>> studentsByDepartment = students.stream()
                .collect(Collectors.groupingBy(Student::getDepartment));

        List<DepartmentSummaryDto> result = new ArrayList<>();

        for (Map.Entry<String, List<Student>> entry : studentsByDepartment.entrySet()) {
            String department = entry.getKey();
            List<Student> departmentStudents = entry.getValue();

            List<Long> studentIds = departmentStudents.stream()
                    .map(Student::getId)
                    .toList();

            List<Enrollment> departmentEnrollments = enrollments.stream()
                    .filter(e -> studentIds.contains(e.getStudentId()))
                    .toList();

            List<Double> cgpas = new ArrayList<>();

            for (Student student : departmentStudents) {
                cgpas.add(getTranscript(student.getId()).getCgpa());
            }

            double averageCgpa = cgpas.isEmpty()
                    ? 0.0
                    : cgpas.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);

            String popularCourse = "None";

            if (!departmentEnrollments.isEmpty()) {
                Map<Long, Long> courseCounts = departmentEnrollments.stream()
                        .collect(Collectors.groupingBy(
                                Enrollment::getCourseId,
                                Collectors.counting()
                        ));

                Long popularCourseId = courseCounts.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse(null);

                if (popularCourseId != null) {
                    popularCourse = courseRepository.findById(popularCourseId)
                            .map(Course::getCode)
                            .orElse("None");
                }
            }

            result.add(new DepartmentSummaryDto(
                    department,
                    departmentStudents.size(),
                    departmentEnrollments.size(),
                    round(averageCgpa),
                    popularCourse
            ));
        }

        return result;
    }

    public List<TopPerformerDto> getTopPerformers(int limit) {
        List<TopPerformerDto> performers = new ArrayList<>();

        for (Student student : studentRepository.findAll()) {
            TranscriptDto transcript = getTranscript(student.getId());

            int coursesPassed = 0;

            for (TranscriptDto.CourseResult course : transcript.getCourses()) {
                if (course.getGrade() != null && course.getGrade() >= 2.00) {
                    coursesPassed++;
                }
            }

            performers.add(new TopPerformerDto(
                    student.getId(),
                    student.getName(),
                    student.getDepartment(),
                    transcript.getTotalCreditsEarned(),
                    coursesPassed,
                    transcript.getCgpa()
            ));
        }

        return performers.stream()
                .sorted(Comparator.comparingDouble(TopPerformerDto::getCgpa).reversed())
                .limit(limit)
                .toList();
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
