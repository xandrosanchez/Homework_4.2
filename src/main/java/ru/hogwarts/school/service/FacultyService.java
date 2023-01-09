package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositorise.FacultyRepository;
import ru.hogwarts.school.repositorise.StudentRepository;

import java.util.*;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    private final StudentRepository studentRepository;

    public FacultyService(FacultyRepository facultyRepository, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Was invoked method for search faculty");
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (findFaculty(faculty.getId()) == null){
            logger.info("Was invoked method for search faculty, but object is null");
            return null;
        }
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color){
        logger.info("Was invoked method for search faculty by color");
        return facultyRepository.findFacultiesByColor(color);
    }

    public Collection<Faculty> findFacultiesByColorOrName(String nameOrColor){
        logger.info("Was invoked method for search faculty by color or name");
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(nameOrColor,nameOrColor);
    }

    public Collection<Student> getStudentsByFaculty(long id) {
        Faculty faculty = findFaculty(id);
        logger.info("Was invoked method for get student by faculty");
        return studentRepository.findAllByFaculty_Id(faculty.getId());
    }
}
