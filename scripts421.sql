Alter Table student
    ADD constraint age check ( age > 15 );

ALTER TABLE student
    ADD CONSTRAINT name UNIQUE (name);
ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE faculty
Add constraint name_color UNIQUE(name,color);

Alter TABLE student
Alter column age set default 20;