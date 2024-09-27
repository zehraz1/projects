DROP DATABASE IF EXISTS MUSEUM;
CREATE DATABASE MUSEUM; 
USE MUSEUM;

DROP TABLE IF EXISTS ART_OBJECT;
CREATE TABLE ART_OBJECT
( id_no           INT   	    NOT NULL,
  Title           VARCHAR(30)   NOT NULL,
  Year_created    INT,
  _Description    TEXT,
  Epoch           VARCHAR(30),
  Origin          VARCHAR(30),
  
  PRIMARY KEY (id_no));

DROP TABLE IF EXISTS ARTIST;
CREATE TABLE ARTIST
( Artist_Name     VARCHAR(30) 	NOT NULL,
  date_born       DATE          NOT NULL,
  main_style 	  VARCHAR(30)   NOT NULL,
  date_died       DATE          NOT NULL,   
  Epoch           VARCHAR(30),
  origin_country  VARCHAR(30),
  _description    TEXT,
  
  PRIMARY KEY (Artist_Name));

DROP TABLE IF EXISTS EXHIBITION;
CREATE TABLE EXHIBITION
( Exhibition_Name            VARCHAR(30)   NOT NULL,
  startdate                  DATE          NOT NULL,
  enddate                    DATE          NOT NULL,

  PRIMARY KEY (Exhibition_Name));

DROP TABLE IF EXISTS _COLLECTION;
CREATE TABLE _COLLECTION
( Collection_Name                      VARCHAR(30)      NOT NULL,
  Phone                                CHAR(20)         NOT NULL,
  Collection_description               TEXT,
  Contact_person                       VARCHAR(20),
  Collection_Type                      VARCHAR(20),
  Collection_Address                   TEXT,
  
  primary key (Collection_Name));

DROP TABLE IF EXISTS PAINTING;
CREATE TABLE PAINTING
( id_no          INT            NOT NULL,
  paint_type     VARCHAR(20)    NOT NULL,
  drawn_on       VARCHAR(20)    NOT NULL,
  Style          VARCHAR(20)    NOT NULL,

  CONSTRAINT FOREIGN KEY (id_no) REFERENCES ART_OBJECT(id_no)   
  ON DELETE CASCADE ON UPDATE CASCADE);
  
DROP TABLE IF EXISTS SCULPTURE;
CREATE TABLE SCULPTURE
( id_no          INT            NOT NULL,
  Material       VARCHAR(30),
  Height         INT,
  _Weight        INT,
  Style          VARCHAR(30),
  
  CONSTRAINT FOREIGN KEY (id_no) REFERENCES ART_OBJECT(id_no)   
  ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS STATUE;  
CREATE TABLE STATUE
( id_no          INT            NOT NULL,
  Material       VARCHAR(20),
  Height         INT,
  _Weight        INT,
  Style          VARCHAR(20),

  CONSTRAINT FOREIGN KEY (id_no) REFERENCES ART_OBJECT(id_no)   
  ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS OTHER;
CREATE TABLE OTHER
( id_no          INT            NOT NULL,
  art_type       VARCHAR(20),
  Style          VARCHAR(20),
  
  CONSTRAINT FOREIGN KEY (id_no) REFERENCES ART_OBJECT(id_no)   
  ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS PERMANENT_COLLECTION;  
CREATE TABLE PERMANENT_COLLECTION
( id_no                 INT             NOT NULL,
  current_status        VARCHAR(20),
  Cost                  INT             NOT NULL,
  date_acquired         INT             NOT NULL,
  
  CONSTRAINT FOREIGN KEY (id_no) REFERENCES ART_OBJECT(id_no)   
  ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS BORROWED;  
CREATE TABLE BORROWED
( id_no         INT              NOT NULL,
  date_borrowed DATE             NOT NULL,
  date_returned DATE             NOT NULL,
  
  CONSTRAINT FOREIGN KEY (id_no) REFERENCES ART_OBJECT(id_no)   
  ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS BORROWED_FROM;
CREATE TABLE BORROWED_FROM
( id_no                 INT              NOT NULL,
  Collection_Name       VARCHAR(30)      NOT NULL,
  
  FOREIGN KEY (id_no) REFERENCES BORROWED(id_no) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (Collection_Name) REFERENCES _Collection(Collection_Name) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS _CREATE;  
CREATE TABLE _CREATE
( id_no                INT              NOT NULL,
  Artist_Name          VARCHAR(20)      NOT NULL,
  
  FOREIGN KEY (id_no) REFERENCES ART_OBJECT(id_no) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (Artist_Name) REFERENCES ARTIST(Artist_Name) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TABLE IF EXISTS EXHIBITED_AT;
CREATE TABLE EXHIBITED_AT
( id_no                    INT              NOT NULL,
  Exhibition_Name          VARCHAR(20)      NOT NULL,
  
  FOREIGN KEY (id_no) REFERENCES ART_OBJECT(id_no) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (Exhibition_Name) REFERENCES Exhibition(Exhibtion_Name) ON DELETE CASCADE ON UPDATE CASCADE);

DROP TRIGGER IF EXISTS delete_sculpture;
DELIMITER //
CREATE TRIGGER delete_sculpture
AFTER DELETE ON SCULPTURE
FOR EACH ROW
BEGIN
IF (OLD.id_no in (SELECT id_no FROM ART_OBJECT)) THEN
DELETE FROM ART_OBJECT WHERE id_no = old.id_no;
END IF;
END;//
DELIMITER ;

DROP TRIGGER IF EXISTS update_painting;
DELIMITER //
CREATE TRIGGER update_painting
BEFORE UPDATE ON PAINTING
FOR EACH ROW
BEGIN
IF (OLD.id_no <> NEW.id_no)
THEN SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'To change ID_no of painting, make the change in the art_object table.';
END IF;
END;//
DELIMITER ;

INSERT INTO ART_OBJECT
VALUES      (987654321, 'Mona Lisa', '1506', 'Portrait of Woman', 'Renaissance', 'France'),
            (876543210, 'The Starry Night', '1889', 'Abstract Landscape', 'Modern', 'France'),
            (765432109, 'Romeo and Juliet', '1597', 'Romantic Play', 'Renaissance', 'Italy'),
            (654321098, 'David', '1502', 'Statue of David', 'Renaissance', 'Italy'),
            (543210987, 'Apollo and Daphne', '1622', 'Sculpture of a Myth', 'Baroque', 'Italy');

INSERT INTO ARTIST
VALUES      ('Leonardo Da Vinci', '1452-04-15', 'Sfumato', '1519-05-02', 'Renaissance', 'Italy', 'Oil Painter'),
            ('Vincent Van Gogh', '1853-03-30', 'Post Impressionism', '1890-07-29', 'Post Impressionism', 'Netherlands', 'Oil Painter'),
            ('William Shakespeare', '1564-04-23', 'Tragedies', '1616-04-23', 'Renaissance', 'England', 'Play Writer'),
            ('Michelangelo', '1475-03-06', 'Human Subject', '1564-02-18', 'Renaissance', 'Italy', 'Sculptor and Painter'),
            ('Gian Lorenzo Bernini', '1598-12-07', 'Baroque', '1680-11-28', 'Baroque', 'Italy', 'Sculptor and Architect');

INSERT INTO EXHIBITION
VALUES      ('Paintings Exhibition', '2021-07-07', '2021-09-26'),
            ('Renaissance Exhibition', '2021-08-21', '2021-10-23');

INSERT INTO _COLLECTION
VALUES      ('Baroque Collection', '4036666666', 'Pieces from Baroque Era', 'George Hamilton', 'Museum', '1622-25 Galleria Borghese, Rome')

INSERT INTO PAINTING
VALUES      (987654321, 'Oil', 'Wood Panel', 'Portrait'),
            (876543210, 'Oil', 'Canvas', 'Abstract');

INSERT INTO SCULPTURE
VALUES      (543210987, 'Marble', 243, 866, 'Baroque');

INSERT INTO STATUE
VALUES      (654321098, 'Marble', 517, 5669, 'Classical and Renaissance');

INSERT INTO OTHER
VALUES      (765432109, 'Print', 'Renaissance');

INSERT INTO PERMANENT_COLLECTION
VALUES      (987654321, 'On Display', '900000000',  '2000'),
            (876543210, 'On Display', '100000000',  '2004'),
            (765432109, 'Stored', '100000000',  '1999'),
            (654321098, 'On Display', '500500500',  '1982');

INSERT INTO BORROWED
VALUES      (543210987, '2015', '2020');

INSERT INTO BORROWED_FROM
VALUES      (543210987, 'Baroque Collection');

INSERT INTO _Create
Values      (987654321, 'Leonardo Da Vinci'),
            (876543210, 'Vincent Van Gogh'),
            (765432109, 'William Shakespeare'),
            (654321098, 'Michelangelo'),
            (543210987, 'Gian Lorenzo Bernini');

INSERT INTO EXHIBITED_AT
VALUES      (987654321, 'Paintings Exhibition'),
            (876543210, 'Paintings Exhibition'),
            (987654321, 'Renaissance Exhibition'),
            (765432109, 'Renaissance Exhibition'),
            (654321098, 'Renaissance Exhibition');

DROP ROLE IF EXISTS db_admin@localhost, mid_access@localhost, read_access@localhost;
CREATE ROLE db_admin@localhost, mid_access@localhost, read_access@localhost;
GRANT ALL PRIVILEGES ON MUSEUM.* TO db_admin@localhost;
GRANT SELECT, INSERT, DELETE, UPDATE ON MUSEUM.* TO mid_access@localhost;
GRANT Select ON MUSEUM.* TO read_access@localhost;

DROP USER IF EXISTS adm@localhost;
DROP USER IF EXISTS data_entry@localhost;
DROP USER IF EXISTS guest@localhost;

CREATE USER adm@localhost IDENTIFIED WITH mysql_native_password BY 'password';

CREATE USER guest@localhost;

CREATE USER data_entry@localhost IDENTIFIED WITH mysql_native_password BY 'password';

GRANT db_admin@localhost TO adm@localhost;
GRANT mid_access@localhost TO data_entry@localhost;
GRANT read_access@localhost TO guest@localhost;
SET DEFAULT ROLE ALL TO adm@localhost;
SET DEFAULT ROLE ALL TO data_entry@localhost;
SET DEFAULT ROLE ALL TO guest@localhost;    
