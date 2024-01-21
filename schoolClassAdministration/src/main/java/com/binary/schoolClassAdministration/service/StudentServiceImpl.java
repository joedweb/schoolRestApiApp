package com.binary.schoolClassAdministration.service;

import com.binary.schoolClassAdministration.Entity.Student;
import com.binary.schoolClassAdministration.exception.ResourceNotFoundException;
import com.binary.schoolClassAdministration.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
   private final StudentRepository studentRepository;       //why?

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if(optionalStudent.isPresent()){
            return optionalStudent.get();
        }else{
            throw new ResourceNotFoundException("Student with id: " + id + " not found");
        }
    }

    @Override
    public Student addStudent(Student student) {
       return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudentById(Long id, Student student) {

        Student existingStudent = getStudentById(id);

        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setGrade(student.getGrade());
        existingStudent.setGpa(student.getGpa());

        studentRepository.save(existingStudent);
        return existingStudent;
    }

    @Override
    public List<Student> getStudentByGpa(double gpa) {              //implement as a range of gpa   so "all gpas from 3.0-3.5"
        return studentRepository.getAllStudentByGpa(gpa);
    }

}
