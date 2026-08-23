package com.example.courseenrollment.dto;

import java.util.List;

public class TranscriptDto {

    private Long studentId;
    private String name;
    private String email;
    private String department;
    private int admissionYear;
    private List<CourseResult> courses;
    private int totalCreditsEarned;
    private double cgpa;

    public TranscriptDto() {
    }

    public TranscriptDto(Long studentId, String name, String email, String department,
                         int admissionYear, List<CourseResult> courses,
                         int totalCreditsEarned, double cgpa) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.admissionYear = admissionYear;
        this.courses = courses;
        this.totalCreditsEarned = totalCreditsEarned;
        this.cgpa = cgpa;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAdmissionYear() {
        return admissionYear;
    }

    public void setAdmissionYear(int admissionYear) {
        this.admissionYear = admissionYear;
    }

    public List<CourseResult> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseResult> courses) {
        this.courses = courses;
    }

    public int getTotalCreditsEarned() {
        return totalCreditsEarned;
    }

    public void setTotalCreditsEarned(int totalCreditsEarned) {
        this.totalCreditsEarned = totalCreditsEarned;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public static class CourseResult {

        private String courseCode;
        private String title;
        private int credit;
        private String semester;
        private Double grade;

        public CourseResult() {
        }

        public CourseResult(String courseCode, String title, int credit,
                            String semester, Double grade) {
            this.courseCode = courseCode;
            this.title = title;
            this.credit = credit;
            this.semester = semester;
            this.grade = grade;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public Double getGrade() {
            return grade;
        }

        public void setGrade(Double grade) {
            this.grade = grade;
        }
    }
}
