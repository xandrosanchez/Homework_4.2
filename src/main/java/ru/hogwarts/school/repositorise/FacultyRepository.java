package ru.hogwarts.school.repositorise;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculty;

import java.util.Collection;
public interface FacultyRepository extends JpaRepository<Faculty,Long> {

    Collection<Faculty> findFacultiesByColor(String color);
    Collection<Faculty> findAllByColorIgnoreCaseOrNameIgnoreCase(String color,String name);

}
