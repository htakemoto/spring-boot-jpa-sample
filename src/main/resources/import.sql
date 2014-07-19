-- This file is only executed automatically by JPA (Not Spring) for embedded database.
-- Hence, database table structure is automatically created without CREATE table statement.

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