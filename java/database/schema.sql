BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS photos;
DROP SEQUENCE IF EXISTS seq_photo_id;
DROP TABLE IF EXISTS UserToFavorite;
DROP TABLE IF EXISTS comments;
DROP SEQUENCE IF EXISTS seq_comment_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_photo_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_comment_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE photos(
    photo_id int DEFAULT nextval('seq_photo_id'::regclass) NOT NULL,
    description varchar(1000),
    photo_src varchar(100) NOT NULL,
    likes int,
    datetime timestamp NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT PK_photo PRIMARY KEY (photo_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE UserToFavorite (
    user_id int NOT NULL,
    photo_id int NOT NULL,
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_photo FOREIGN KEY (photo_id) REFERENCES photos(photo_id)
);

CREATE TABLE comments (
    comment_id int DEFAULT nextval('seq_comment_id'::regclass) NOT NULL,
    likes int,
    text varchar(1000) NOT NULL,
    photo_id int NOT NULL,
    datetime timestamp NOT NULL,
    CONSTRAINT PK_comment PRIMARY KEY (comment_id),
    CONSTRAINT FK_photo FOREIGN KEY (photo_id) REFERENCES photos(photo_id)
);

--ROLLBACK;
COMMIT TRANSACTION;
