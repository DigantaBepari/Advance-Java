package com.example.courseenrollment.service;

import com.example.courseenrollment.entity.Student;
import com.example.courseenrollment.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        student.setId(studentRepository.nextId());
        return studentRepository.save(student);
    }

    public List<Student> getStudents(String department) {
        if (department == null || department.isBlank()) {
            return studentRepository.findAll();
        }

        return studentRepository.findAll().stream()
                .filter(student -> student.getDepartment().equalsIgnoreCase(department))
                .toList();
    }

    public Student getStudent(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student student = getStudent(id);

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setDepartment(updatedStudent.getDepartment());
        student.setAdmissionYear(updatedStudent.getAdmissionYear());

        return student;
    }

    public void deleteStudent(Long id) {
        Student student = getStudent(id);
        studentRepository.delete(student);
    }
}
