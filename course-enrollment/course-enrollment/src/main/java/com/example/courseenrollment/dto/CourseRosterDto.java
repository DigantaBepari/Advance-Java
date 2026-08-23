package com.example.courseenrollment.dto;

import java.util.List;

public class CourseRosterDto {

    private Long courseId;
    private String code;
    private String title;
    private int credit;
    private String instructor;
    private int capacity;
    private List<StudentInfo> enrolledStudents;
    private int seatsFilled;
    private int seatsRemaining;
    private double classAverageGrade;

    public CourseRosterDto() {
    }

    public CourseRosterDto(Long courseId, String code, String title, int credit,
                           String instructor, int capacity,
                           List<StudentInfo> enrolledStudents,
                           int seatsFilled, int seatsRemaining,
                           double classAverageGrade) {
        this.courseId = courseId;
        this.code = code;
        this.title = title;
        this.credit = credit;
        this.instructor = instructor;
        this.capacity = capacity;
        this.enrolledStudents = enrolledStudents;
        this.seatsFilled = seatsFilled;
        this.seatsRemaining = seatsRemaining;
        this.classAverageGrade = classAverageGrade;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<StudentInfo> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<StudentInfo> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public int getSeatsFilled() {
        return seatsFilled;
    }

    public void setSeatsFilled(int seatsFilled) {
        this.seatsFilled = seatsFilled;
    }

    public int getSeatsRemaining() {
        return seatsRemaining;
    }

    public void setSeatsRemaining(int seatsRemaining) {
        this.seatsRemaining = seatsRemaining;
    }

    public double getClassAverageGrade() {
        return classAverageGrade;
    }

    public void setClassAverageGrade(double classAverageGrade) {
        this.classAverageGrade = classAverageGrade;
    }

    public static class StudentInfo {

        private Long studentId;
        private String name;
        private String email;
        private String department;

        public StudentInfo() {
        }

        public StudentInfo(Long studentId, String name, String email, String department) {
            this.studentId = studentId;
            this.name = name;
            this.email = email;
            this.department = department;
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
    }
}
