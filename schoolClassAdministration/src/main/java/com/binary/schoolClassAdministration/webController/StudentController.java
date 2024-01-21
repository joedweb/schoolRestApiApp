package com.binary.schoolClassAdministration.webController;

import com.binary.schoolClassAdministration.Entity.Student;
import com.binary.schoolClassAdministration.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school/v1/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getstudents(){
        return new ResponseEntity<>(studentService.getStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentById(id);       //outside because it is not returning the student from the Response. Just deleting it!!
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
public ResponseEntity<Student> updateStudentById(@PathVariable Long id, @RequestBody Student student){
        return new ResponseEntity<>(studentService.updateStudentById(id,student), HttpStatus.ACCEPTED);
    }

    @GetMapping("/gpa/{gpa}")
    public ResponseEntity<List<Student>> getStudentByGpa(@PathVariable double gpa){
        return new ResponseEntity<>(studentService.getStudentByGpa(gpa), HttpStatus.OK);
    }


}

