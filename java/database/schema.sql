BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS photos;
DROP SEQUENCE IF EXISTS seq_photo_id;
DROP TABLE IF EXISTS UserToFavorite;
DROP TABLE IF EXISTS comments;
DROP SEQUENCE IF EXISTS seq_comment_id;
DROP TABLE IF EXISTS photo_to_likes;

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
	name varchar(70) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE photos(
    photo_id int DEFAULT nextval('seq_photo_id'::regclass) NOT NULL,
    description varchar(1000),
    photo_src varchar(100) NOT NULL,
    likes int,
    datetime timestamp NOT NULL,
    user_id int NOT NULL,
    profile boolean DEFAULT false,
    CONSTRAINT PK_photo PRIMARY KEY (photo_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE photo_to_likes(
    photo_id int NOT NULL,
    user_id int NOT NULL,
    CONSTRAINT FK_photo FOREIGN KEY (photo_id) REFERENCES photos(photo_id),
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
    user_id int NOT NULL,
    datetime timestamp NOT NULL,
    CONSTRAINT PK_comment PRIMARY KEY (comment_id),
    CONSTRAINT FK_photo FOREIGN KEY (photo_id) REFERENCES photos(photo_id),
    CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

--Users
INSERT INTO users (username,password_hash,role, name) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'user');
INSERT INTO users (username,password_hash,role, name) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'admin');


--PASSWORD For all: test
INSERT INTO users (username, password_hash, role, name) values ('user1@email.com', '$2a$10$GQkjEhtiXfN5dOKkXwWm4eazZG1kqvWrhoGllzXmaFiO5YT0P6oTK', 'ROLE_USER', 'user1');
INSERT INTO users (username, password_hash, role, name) values ('user2@email.com', '$2a$10$GQkjEhtiXfN5dOKkXwWm4eazZG1kqvWrhoGllzXmaFiO5YT0P6oTK', 'ROLE_USER', 'user2');
INSERT INTO users (username, password_hash, role, name) values ('user3@email.com', '$2a$10$GQkjEhtiXfN5dOKkXwWm4eazZG1kqvWrhoGllzXmaFiO5YT0P6oTK', 'ROLE_USER', 'user3');


--PHOTOS
INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data1', 'dummy src', 0, current_timestamp, 3);
INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data2', 'dummy src', 0, current_timestamp, 3);
INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data3', 'dummy src', 0, current_timestamp, 3);

INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data4', 'dummy src', 0, current_timestamp, 4);
INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data5', 'dummy src', 0, current_timestamp, 4);
INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data6', 'dummy src', 0, current_timestamp, 4);

INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data7', 'dummy src', 0, current_timestamp, 5);
INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data8', 'dummy src', 0, current_timestamp, 5);
INSERT INTO photos(description, photo_src, likes, datetime, user_id) VALUES ('Dummy data9', 'dummy src', 0, current_timestamp, 5);

--USERSTOFAVORITES
INSERT INTO UserToFavorite (user_id, photo_id) VALUES (3, 7);
INSERT INTO UserToFavorite (user_id, photo_id) VALUES (3, 8);
INSERT INTO UserToFavorite (user_id, photo_id) VALUES (3, 9);

INSERT INTO UserToFavorite (user_id, photo_id) VALUES (4, 1);
INSERT INTO UserToFavorite (user_id, photo_id) VALUES (4, 2);
INSERT INTO UserToFavorite (user_id, photo_id) VALUES (4, 3);

INSERT INTO UserToFavorite (user_id, photo_id) VALUES (5, 4);
INSERT INTO UserToFavorite (user_id, photo_id) VALUES (5, 5);
INSERT INTO UserToFavorite (user_id, photo_id) VALUES (5, 6);

--COMMENTS
INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (3, 'Dummy text1', 1, 4, current_timestamp);
INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (1, 'Dummy text2', 2, 4, current_timestamp);
INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (0, 'Dummy text3', 3, 4, current_timestamp);

INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (1, 'Dummy text4', 4, 5, current_timestamp);
INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (5, 'Dummy text5', 5, 5, current_timestamp);
INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (6, 'Dummy text6', 6, 5, current_timestamp);

INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (3, 'Dummy text7', 7, 3, current_timestamp);
INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (1, 'Dummy text8', 8, 3, current_timestamp);
INSERT INTO comments (likes, text, photo_id, user_id, datetime) VALUES (0, 'Dummy text9', 9, 3, current_timestamp);

--ROLLBACK;
COMMIT TRANSACTION;
