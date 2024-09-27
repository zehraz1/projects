USE MUSEUM

--Q1
SELECT * FROM ART_OBJECT;
SELECT * FROM ARTIST;
SELECT * FROM EXHIBITION;
SELECT * FROM _COLLECTION;
SELECT * FROM PAINTING;
SELECT * FROM SCULPTURE;
SELECT * FROM STATUE;
SELECT * FROM OTHER;
SELECT * FROM PERMANENT_COLLECTION;
SELECT * FROM BORROWED;
SELECT * FROM BORROWED_FROM;
SELECT * FROM _CREATE;
SELECT * FROM EXHIBITED_AT;
/*
These tables are related in many ways. 
ART_OBJECT, ARTIST, EXHIBITION and _Collection are strong entities each with their own primary key.
PAINTING, SCULPTURE, STATUE and OTHER are subclasses of ART_OBJECT so their foreign key is the primary key of ART_OBJECT: id_no.
PERMANENT_COLLECTION and BORROWED are also subclasses of ART_OBJECT so their foreign key is also id_no of ART_OBJECT.
Because of these subclasses and their foreign keys we have included multiple constraints and 2 custom triggers in our sql scirpt. 
_CREATE EXHIBITED_AT and BORROWED_FROM are M:N relationships between 2 entites so they need their seperate table. They use the primary keys of the 2 enitites they connect as attributes.
*/

--Q2
SELECT id_no
FROM  ART_OBJECT;

--Q3
SELECT Artist_Name 
FROM ARTIST
ORDER BY date_born ASC;

--Q4
SELECT Title 
FROM ART_OBJECT
WHERE Year_created IN (
    SELECT Year_created 
    FROM ART_OBJECT
    WHERE Epoch = 'Renaissance');

--Q5
SELECT Title, Epoch
FROM ART_OBJECT
JOIN ARTIST ON ARTIST.Epoch = ART_OBJECT.Epoch;

--Q6
UPDATE PAINTING
SET drawn_on = 'Concrete'
WHERE id_no = '987654321';
SELECT * FROM PAINTING;

-- additional update using custom trigger: update_painting

UPDATE PAINTING 
SET id_no = '716728167'
WHERE id_no = '987654321';
SELECT * FROM PAINTING;

--Q7 (deletion using custom trigger: delete_sculpture)
DELETE FROM SCULPTURE
WHERE id_no = '987654321';

SELECT * FROM SCULPTURE;
SELECT * FROM ART_OBJECT;

