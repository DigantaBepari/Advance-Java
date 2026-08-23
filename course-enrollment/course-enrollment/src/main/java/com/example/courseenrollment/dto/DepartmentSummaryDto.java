package com.example.courseenrollment.dto;

public class DepartmentSummaryDto {

    private String department;
    private int numberOfStudents;
    private int totalEnrollments;
    private double averageCgpa;
    private String mostPopularCourse;

    public DepartmentSummaryDto() {
    }

    public DepartmentSummaryDto(String department, int numberOfStudents,
                                int totalEnrollments, double averageCgpa,
                                String mostPopularCourse) {
        this.department = department;
        this.numberOfStudents = numberOfStudents;
        this.totalEnrollments = totalEnrollments;
        this.averageCgpa = averageCgpa;
        this.mostPopularCourse = mostPopularCourse;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public int getTotalEnrollments() {
        return totalEnrollments;
    }

    public void setTotalEnrollments(int totalEnrollments) {
        this.totalEnrollments = totalEnrollments;
    }

    public double getAverageCgpa() {
        return averageCgpa;
    }

    public void setAverageCgpa(double averageCgpa) {
        this.averageCgpa = averageCgpa;
    }

    public String getMostPopularCourse() {
        return mostPopularCourse;
    }

    public void setMostPopularCourse(String mostPopularCourse) {
        this.mostPopularCourse = mostPopularCourse;
    }
}
