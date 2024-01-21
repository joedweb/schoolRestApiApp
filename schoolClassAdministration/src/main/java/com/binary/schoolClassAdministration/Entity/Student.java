package com.binary.schoolClassAdministration.Entity;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private int grade;
    private double gpa;

    @ManyToOne(fetch = FetchType.LAZY)      //"many students to one teacher"
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    public Student(){       //empty constructor for the entity. Hibernate uses?
    }


    //Constructor
    public Student(String firstName, String lastName, int grade, double gpa, Teacher teacher) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.gpa = gpa;
        this.teacher = teacher;
    }


    //Setter and Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
