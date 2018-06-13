DROP TABLE IF EXISTS CONTACT;

CREATE TABLE CONTACT
(id int primary key,
  name varchar(255),
  address varchar(255));

CREATE SEQUENCE contact_id_seq START 1;