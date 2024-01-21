package com.binary.schoolClassAdministration.service;

import com.binary.schoolClassAdministration.Entity.Student;
import com.binary.schoolClassAdministration.Entity.Teacher;
import com.binary.schoolClassAdministration.exception.ResourceNotFoundException;
import com.binary.schoolClassAdministration.repository.TeacherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getTeachers() {
        return (List<Teacher>) teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Car with id: " +id+ " not found"));
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher updateTeacherById(Long id, Teacher teacher) {

        Teacher existingTeacher = getTeacherById(id);       //checking if it exists

        existingTeacher.setFirstName(teacher.getFirstName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setSubject(teacher.getSubject());

        teacherRepository.save(existingTeacher);
        return existingTeacher;
    }

    @Override
    public List<Teacher> getTeacherBySubject(String subject) {
        return teacherRepository.getTeacherBySubject(subject);
    }
}
