package com.binary.schoolClassAdministration.service;

import com.binary.schoolClassAdministration.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();

    Student getStudentById(Long id);

    Student addStudent(Student student);

    void deleteStudentById(Long id);

    Student updateStudentById(Long id, Student student);

    List<Student> getStudentByGpa(double gpa);
}
