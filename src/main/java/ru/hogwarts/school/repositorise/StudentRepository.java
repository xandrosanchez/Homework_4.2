package ru.hogwarts.school.repositorise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Collection<Student> findByAge(int age);
    Collection<Student> findAllByAgeBetween(int age1, int age2);
    Collection<Student> findAllByFaculty_Id(long faculty_id);
    @Query(value = "SELECT count(name) FROM student",nativeQuery = true)
    int getNumberOfStudents();

    @Query(value = "SELECT avg(age) FROM student", nativeQuery = true)
    int getAVGAgeOfStudents();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5",nativeQuery = true)
    Collection<Student> get5LastStudents();

}
