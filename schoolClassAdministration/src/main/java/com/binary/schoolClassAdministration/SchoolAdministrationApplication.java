package com.binary.schoolClassAdministration;

import com.binary.schoolClassAdministration.Entity.Student;
import com.binary.schoolClassAdministration.Entity.Teacher;
import com.binary.schoolClassAdministration.Entity.User;
import com.binary.schoolClassAdministration.repository.StudentRepository;
import com.binary.schoolClassAdministration.repository.TeacherRepository;
import com.binary.schoolClassAdministration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
public class SchoolAdministrationApplication implements CommandLineRunner{

        @Autowired
        private StudentRepository studentRepository;        //again why?
        @Autowired
        private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;


        private static final Logger logger = LoggerFactory.getLogger(SchoolAdministrationApplication.class);

        public static void main(String[] args) {
            SpringApplication.run(SchoolAdministrationApplication.class, args);
            logger.info("Application started");
        }

        @Override
        public void run(String... args) throws Exception {


        Teacher teacher1 = new Teacher("Linda", "Soto", "Trigonometry");
            Teacher teacher2 = new Teacher("Jonathan", "Smith","Biology");
            Teacher teacher3 = new Teacher("Carmen", "Noble", "Astronomy");
            Teacher teacher4 = new Teacher("Amy", "Rodriguez", "Environmental Chemistry");
        teacherRepository.save(teacher1);
            teacherRepository.save(teacher2);
            teacherRepository.save(teacher3);
            teacherRepository.save(teacher4);


        List<Student> students = Arrays.asList(
         new Student("William", "Hernandez", 91, 3.65, teacher1),
                new Student("Maria", "Lopez", 85, 3.21, teacher3),
                new Student("Karla", "Carter", 90,3.57,teacher3),
                new Student("Natalia", "Lexington", 75, 2.91, teacher4),
                new Student("John", "Doe", 86, 3.79, teacher1),
                new Student("Jack", "McCandles", 93, 4.0, teacher2)
    );



        studentRepository.saveAll(students);

        studentRepository
                .findAll()
                .forEach(student -> logger.info(student.getFirstName()+ " " + student.getLastName()+ ": "+ student.getGpa()));

        teacherRepository.findAll().forEach(teach -> logger.info(teach.getLastName()));


            // SECURITY

            userRepository.save(new User("user", "$2y$12$Ze.Piikopw4og0jYRM3aCO9WUYrdFXPzdBElZ5pe5f55CGXkOS9GC", "USER"));
            userRepository.save(new User("admin","$2y$12$7A1GXnAqDn9g.f3sFa3LCeOGVJrR6JOwGh3roWIyIRuAx.OLPgdzG" ,"ADMIN"));


        }
    }

