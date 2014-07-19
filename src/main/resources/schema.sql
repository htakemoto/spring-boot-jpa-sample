-- This file is only executed automatically by Spring for external database.
-- Uncomment SQL config part on application.yml.

DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
  user_id    INT(10) UNSIGNED      NOT NULL AUTO_INCREMENT,
  first_name CHARACTER VARYING(50) NOT NULL,
  last_name  CHARACTER VARYING(20),
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE item (
  item_id    INT(10) UNSIGNED      NOT NULL AUTO_INCREMENT,
  item_name  CHARACTER VARYING(50) NOT NULL,
  quantity   INT(3)                NOT NULL,
  user_id    INT(10) UNSIGNED      NOT NULL,
  PRIMARY KEY (item_id),
  CONSTRAINT user_id FOREIGN KEY (user_id) REFERENCES user (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (first_name, last_name) VALUES ('Ben', 'Linus');
INSERT INTO user (first_name, last_name) VALUES ('Claire', 'Littleton');
INSERT INTO user (first_name, last_name) VALUES ('Desmond', 'Hume');
INSERT INTO user (first_name, last_name) VALUES ('Frank', 'Lapidus');
INSERT INTO user (first_name, last_name) VALUES ('Hugo Reyes', 'Hurley');
INSERT INTO user (first_name, last_name) VALUES ('Jack', 'Shephard');
INSERT INTO user (first_name, last_name) VALUES ('James Ford', 'Sawyer');
INSERT INTO user (first_name, last_name) VALUES ('Jin-Soo', 'Kwon');
INSERT INTO user (first_name, last_name) VALUES ('Miles', 'Straume');
INSERT INTO user (first_name, last_name) VALUES ('Richard', 'Alpert');
INSERT INTO user (first_name, last_name) VALUES ('Sayid', 'Jarrah');
INSERT INTO user (first_name, last_name) VALUES ('Sun-Hwa', 'Kwon');

INSERT INTO item (item_name, quantity, user_id) VALUES ('Apple Powerbook G4', 1, (SELECT user_id FROM user WHERE user_id=1));
INSERT INTO item (item_name, quantity, user_id) VALUES ('Lenovo IdeaPad Z510', 1, (SELECT user_id FROM user WHERE user_id=1));
INSERT INTO item (item_name, quantity, user_id) VALUES ('Drama Friends', 1, (SELECT user_id FROM user WHERE user_id=2));
INSERT INTO item (item_name, quantity, user_id) VALUES ('Spirit Away', 1, (SELECT user_id FROM user WHERE user_id=2));
INSERT INTO item (item_name, quantity, user_id) VALUES ('Gameboy Advance', 1, (SELECT user_id FROM user WHERE user_id=3));
INSERT INTO item (item_name, quantity, user_id) VALUES ('Pokemon', 1, (SELECT user_id FROM user WHERE user_id=3));