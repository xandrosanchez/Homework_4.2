package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorise.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    private int threadSyncCounter = 0;

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
        return studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(Double.NaN);
    }

    public Collection<Student> get5LastStudents(){
        logger.info("Was invoked method for get 5 last students");
        return studentRepository.get5LastStudents();
    }

    public Collection<String> getStudentsWithNameStartingWithA(){
        logger.info("Was invoked method for get students with name starting with A");
        return studentRepository.findAll()
                .stream()
                .parallel()
                .map(Student::getName)
                .filter(s -> s.startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
    }

    public void getAllStudentNames() {
        List<String> names = studentRepository.findAll().stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println(names.get(0));
        System.out.println(names.get(1));

        Thread thread1 = new Thread(() -> {
            System.out.println(names.get(2));
            System.out.println(names.get(3));
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println(names.get(4));
            System.out.println(names.get(5));
        });
        thread2.start();
    }

    public void getAllStudentNamesSync() {
        List<String> names = studentRepository.findAll().stream()
                .map(Student::getName)
                .collect(Collectors.toList());

        printSync(names);
        printSync(names);

        Thread thread1 = new Thread(() -> {
            printSync(names);
            printSync(names);
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            printSync(names);
            printSync(names);
        });
        thread2.start();
    }

    private synchronized void printSync(List<String> names) {
        System.out.println(names.get(threadSyncCounter));
        threadSyncCounter++;
    }
}
