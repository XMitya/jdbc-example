-- DDL --

DROP TABLE IF EXISTS grade;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS course;

CREATE TABLE public.course (
  id SERIAL PRIMARY KEY,
  title VARCHAR(255),
  duration SMALLINT,
  price NUMERIC
);

CREATE TABLE public.teacher (
  id SERIAL PRIMARY KEY,
  name CHARACTER VARYING(255),
  salary NUMERIC,
  course_id INT REFERENCES public.course (id) ON DELETE RESTRICT
);

CREATE TABLE public.student (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  course_id INT NOT NULL REFERENCES public.course (id) ON DELETE RESTRICT,
  teacher_id INT REFERENCES public.teacher (id) ON DELETE RESTRICT
);

CREATE TABLE public.grade (
  id SERIAL PRIMARY KEY,
  student_id INT NOT NULL REFERENCES public.student (id) ON DELETE RESTRICT,
  course_id INT NOT NULL REFERENCES public.course (id) ON DELETE RESTRICT,
  teacher_id INT NOT NULL REFERENCES public.teacher (id) ON DELETE RESTRICT,
  description TEXT,
  grade SMALLINT
);

-- DML --

INSERT INTO public.course (title, duration, price) VALUES ('Python', 150, 20000.00);
INSERT INTO public.course (title, duration, price) VALUES ('PHP', 174, 25000.00);
INSERT INTO public.course (title, duration, price) VALUES ('Ruby', 135, 17000.00);
INSERT INTO public.course (title, duration, price) VALUES ('Java', 145, 30000.00);
INSERT INTO public.course (title, duration, price) VALUES ('Scala', 120, 18000.00);
INSERT INTO public.course (title, duration, price) VALUES ('Clojure', 90, 13000.00);
INSERT INTO public.course (title, duration, price) VALUES ('Haskell', 156, 9000.00);

INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Ivan Drago', 15000.00 , 1);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Chuck Norris', 18000.00 , 2);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Takeshi Kitano', 7000.00 , 3);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('James Gosling', 357000.00 , 4);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Harry Potter', 7000.00 , NULL);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('John Doe', 15345.00 , NULL);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Pragmatic Coder', 0.00 , 2);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Hoolio', 34600.00 , 4);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Баррикада', 34600.00 , 1);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Революция', 34600.00 , 1);
INSERT INTO public.teacher ( name, salary, course_id) VALUES ('Пострел Везде Поспел', 34600.00 , 3);

INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Вовочка', 1, 1);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Рабинович', 2, 7);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Bon Jovi', 3, 11);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Овощной суп', 3, 3);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Крестный Отец', 4, 4);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Ronny Sallivan', 3, 11);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Tommy Vercetti', 4, 4);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Mark Zukerberg', 2, 7);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Beavis', 2, 2);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Butthead', 2, 2);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Eric Cartman', 4, 8);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Kyle Broflovsky', 1, 1);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Kenny McCormick', 1, 1);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Stan Marsh', 3, 3);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Штурмовик', 4, 8);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Беовульф', 4, 4);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Тухлое яйцо', 2, 2);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Оциллококцинум', 2, 7);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Павлик Морозов', 3, 11);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Маша Ботанова', 4, 8);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Алиса Селезнева', 4, 4);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Владимир Владимирович', 7, NULL);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Борман', 5, NULL);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Миша 2 процента', 7, NULL);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Гепа', 7, NULL);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Гена', 7, NULL);
INSERT INTO public.student ( name, course_id, teacher_id) VALUES ('Чебурашка', 7, NULL);


-- Generate grands --
DO $$

DECLARE
  r student%ROWTYPE;

BEGIN
  FOR r IN
    SELECT * FROM student WHERE course_id IS NOT NULL AND student.teacher_id IS NOT NULL
    LOOP
      INSERT INTO grade (student_id, course_id, teacher_id, grade) VALUES (r.id, r.course_id, r.teacher_id, (SELECT floor(random()*(5-2+1))+2));
      INSERT INTO grade (student_id, course_id, teacher_id, grade) VALUES (r.id, r.course_id, r.teacher_id, (SELECT floor(random()*(5-2+1))+2));
      INSERT INTO grade (student_id, course_id, teacher_id, grade) VALUES (r.id, r.course_id, r.teacher_id, (SELECT floor(random()*(5-2+1))+2));
  END LOOP;
END $$

