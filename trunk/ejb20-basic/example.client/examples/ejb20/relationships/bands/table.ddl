DROP TABLE fanclubs;

DROP TABLE recordings;

DROP TABLE band_artist;

DROP TABLE artist_sequence;

ALTER TABLE artists DROP CONSTRAINT artists_pk CASCADE;
DROP TABLE artists;

ALTER TABLE bands DROP CONSTRAINT bands_pk CASCADE;
DROP TABLE bands;

CREATE TABLE bands (
   name VARCHAR(50),
   founder VARCHAR(50),
   startDate date,
   CONSTRAINT bands_pk PRIMARY KEY
     (name, founder));

CREATE TABLE recordings (
   title VARCHAR(50),
   bandName VARCHAR(50),
   bandFounder VARCHAR(50),
   numberSold INT,
   sales NUMERIC(10, 2),
   recordingDate DATE,
   CONSTRAINT recordings_pk PRIMARY KEY
     (title, bandName, bandFounder),
   CONSTRAINT recordings_fk FOREIGN KEY
     (bandName, bandFounder)
     REFERENCES bands(name, founder));

CREATE TABLE fanclubs (
   text VARCHAR(1024),
   bandName VARCHAR(50),
   bandFounder VARCHAR(50),
   memberCount INT,
   CONSTRAINT fanclubs_pk PRIMARY KEY
     (bandName, bandFounder),
   CONSTRAINT fanclubs_fk FOREIGN KEY
     (bandName, bandFounder)
     REFERENCES bands(name, founder));

CREATE TABLE artists (
   name VARCHAR(50), 
   id INT CONSTRAINT artists_pk PRIMARY KEY);

CREATE TABLE band_artist (
   band_name VARCHAR(50),
   band_founder VARCHAR(50),
   artist_id INT,
   CONSTRAINT band_artist_fk FOREIGN KEY
     (band_name, band_founder)
     REFERENCES bands(name, founder),
   CONSTRAINT band_artist_fk2 FOREIGN KEY
     (artist_id)
     REFERENCES artists(id));

CREATE TABLE artist_sequence (sequence INT);

INSERT INTO artist_sequence VALUES (1);

commit;
