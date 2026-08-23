package com.example.courseenrollment.dto;

public class TopPerformerDto {

    private Long studentId;
    private String name;
    private String department;
    private int creditsCompleted;
    private int coursesPassed;
    private double cgpa;

    public TopPerformerDto() {
    }

    public TopPerformerDto(Long studentId, String name, String department,
                           int creditsCompleted, int coursesPassed, double cgpa) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.creditsCompleted = creditsCompleted;
        this.coursesPassed = coursesPassed;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCreditsCompleted() {
        return creditsCompleted;
    }

    public void setCreditsCompleted(int creditsCompleted) {
        this.creditsCompleted = creditsCompleted;
    }

    public int getCoursesPassed() {
        return coursesPassed;
    }

    public void setCoursesPassed(int coursesPassed) {
        this.coursesPassed = coursesPassed;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
