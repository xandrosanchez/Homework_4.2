package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorise.StudentRepository;

import java.util.*;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for search student");
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        if (findStudent(student.getId()) == null){
            logger.warn("Was invoked method for edit student, but object is null");
            return null;
        }
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for search for a student by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int age1, int age2){
        logger.info("Was invoked method for search for a student by age");
        return studentRepository.findAllByAgeBetween(age1,age2);
    }

    public Faculty getFacultyByStudent(long id){
        logger.info("Was invoked method for get a faculty by student");
        return findStudent(id).getFaculty();
    }

    public int getNumberOfStudents(){
        logger.info("Was invoked method for get number of students");
        return studentRepository.getNumberOfStudents();
    }

    public double getAverageAgeOfStudents(){
        logger.info("Was invoked method for get average age of students");
        return studentRepository.getAVGAgeOfStudents();
    }

    public Collection<Student> get5LastStudents(){
        logger.info("Was invoked method for get 5 last students");
        return studentRepository.get5LastStudents();
    }
}
