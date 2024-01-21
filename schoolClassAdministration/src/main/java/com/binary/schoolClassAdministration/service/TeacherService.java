package com.binary.schoolClassAdministration.service;

import com.binary.schoolClassAdministration.Entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers();

    Teacher getTeacherById(Long id);

    Teacher addTeacher(Teacher teacher);

    void deleteTeacherById(Long id);

    Teacher updateTeacherById(Long id, Teacher teacher);

    List<Teacher> getTeacherBySubject(String subject);
}
