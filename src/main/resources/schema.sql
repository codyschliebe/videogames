DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS game_genre;
DROP TABLE IF EXISTS game;
DROP TABLE IF EXISTS console;

CREATE TABLE console (
console_id INT AUTO_INCREMENT NOT NULL,
console_name VARCHAR(48) NOT NULL,
console_generation VARCHAR(2) NOT NULL,
console_manufacturer VARCHAR(24) NOT NULL),
PRIMARY KEY (console_id)
);

CREATE TABLE game (
game_id INT AUTO_INCREMENT NOT NULL,
game_title VARCHAR(32) NOT NULL,
game_release_year VARCHAR(4) NOT NULL,
game_developer VARCHAR(32) NOT NULL,
game_series VARCHAR(32) NOT NULL,
console_id INT NOT NULL,
PRIMARY KEY (game_id),
FOREIGN KEY (console_id) REFERENCES console (console_id) ON DELETE NO ACTION
);

CREATE TABLE genre (
genre_id INT AUTO_INCREMENT NOT NULL,
genre_name VARCHAR(32) NOT NULL,
genre_type VARCHAR(32) NOT NULL,
PRIMARY KEY (genre_id)
);

CREATE TABLE game_genre (
game_id INT AUTO_INCREMENT NOT NULL,
genre_id INT AUTO_INCREMENT NOT NULL,
FOREIGN KEY (game_id) REFERENCES game (game_id),
FOREIGN KEY (genre_id) REFERENCES genre (genre_id),
UNIQUE KEY (game_id, genre_id)
);

CREATE TABLE review (
review_id INT AUTO_INCREMENT NOT NULL,
game_id INT AUTO_INCREMENT NOT NULL,
review_score INT NOT NULL,
review_text TEXT NOT NULL,
PRIMARY KEY (review_id),
FOREIGN KEY (game_id) REFERENCES game (game_id) ON DELETE CASCADE
);