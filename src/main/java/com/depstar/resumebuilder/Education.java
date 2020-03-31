package com.depstar.resumebuilder;

public class Education {

    String course,institute,cgpa,year;

    public Education(String course, String institute, String cgpa, String year) {
        this.course = course;
        this.institute = institute;
        this.cgpa = cgpa;
        this.year = year;
    }

    public String getCourse() {
        return course;
    }

    public String getInstitute() {
        return institute;
    }

    public String getCgpa() {
        return cgpa;
    }

    public String getYear() {
        return year;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
