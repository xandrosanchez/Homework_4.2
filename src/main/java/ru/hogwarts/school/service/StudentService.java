package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorise.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        if (findStudent(student.getId()) == null){
            return null;
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int age1, int age2){
        return studentRepository.findAllByAgeBetween(age1,age2);
    }

    public Faculty getFacultyByStudent(long id){
        return findStudent(id).getFaculty();
    }

    public int getNumberOfStudents(){
        return studentRepository.getNumberOfStudents();
    }

    public int getAverageAgeOfStudents(){
        return studentRepository.getAVGAgeOfStudents();
    }

    public Collection<Student> get5LastStudents(){
        return studentRepository.get5LastStudents();
    }
}
