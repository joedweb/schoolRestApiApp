package com.binary.schoolClassAdministration.webController;

import com.binary.schoolClassAdministration.Entity.Teacher;
import com.binary.schoolClassAdministration.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getTeachers(){
        return new ResponseEntity<>(teacherService.getTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id){
        return new ResponseEntity<>(teacherService.getTeacherById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Teacher> addTeacher(@RequestBody Teacher teacher){
        return new ResponseEntity<>(teacherService.addTeacher(teacher),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Teacher> deleteTeacherById(@PathVariable Long id){
        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacherById(@PathVariable Long id, @RequestBody Teacher teacher){
        return new ResponseEntity<>(teacherService.updateTeacherById(id, teacher), HttpStatus.ACCEPTED);
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity<List<Teacher>> getTeacherBySubject(@PathVariable String subject){
        return new ResponseEntity<>(teacherService.getTeacherBySubject(subject), HttpStatus.OK);
    }
}
