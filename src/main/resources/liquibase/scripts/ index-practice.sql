- liquibase formatted sql

- changeset aSnegireff:1
    CREATE INDEX student_name_index ON student (name);

- changeset aSnegireff:2
CREATE INDEX faculty_name_color_index ON faculty (name, color);