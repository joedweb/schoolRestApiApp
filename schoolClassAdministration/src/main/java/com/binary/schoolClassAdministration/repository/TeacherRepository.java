package com.binary.schoolClassAdministration.repository;

import com.binary.schoolClassAdministration.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> getTeacherBySubject(String subject);
}
