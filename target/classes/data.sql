INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Atari", "2600", "2");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Nintendo", "Nintendo Entertainment System", "3");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sega", "Master System", "3");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sega", "Genesis", "4");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Nintendo", "Super Nintendo Entertainment System", "4");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sega", "Saturn", "5");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sony", "Playstation", "5");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Nintendo", "Nintendo 64", "5");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sega", "Dreamcast", "6");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sony", "Playstation 2", "6");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Nintendo", "Gamecube", "6");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Microsoft", "Xbox", "6");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Microsoft", "Xbox 360", "7");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sony", "Playstation 3", "7");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Nintendo", "Wii", "7");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Nintendo", "Wii U", "8");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sony", "Playstation 4", "8");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Microsoft", "Xbox One", "8");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Nintendo", "Switch", "8");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Microsoft", "Xbox Series X/S", "9");

INSERT INTO console (console_manufacturer, console_name, console_generation)
VALUES ("Sony", "Playstation 5", "9");


INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Berzerk", "1980", "Stern Electronics", "n/a", 1);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("DuckTales", "1989", "Capcom", "DuckTales", 2);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Final Fantasy VI", "1994", "Squaresoft", "Final Fantasy", 5);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Super Metroid", "1994", "Nintendo", "Metroid", 5);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Perfect Dark", "2000", "Rare", "Perfect Dark", 8);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Castlevania: Symphony of the Night", "1997", "Konami", "Castlevania", 7);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Legend of Zelda: The Wind Waker", "2002", "Nintendo", "Zelda", 11);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Paper Mario: The Thousand Year Door", "2004", "Nintendo", "Paper Mario", 11);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Metal Gear Solid 3: Snake Eater", "2004", "Konami", "Metal Gear", 10);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Braid", "2008", "Jonathan Blow", "n/a", 13);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Dark Souls: Remastered", "2018", "From Soft", "Dark Souls", 17);

INSERT INTO game (game_title, game_release_year, game_developer, game_series, console_id)
VALUES ("Super Mario Maker 2", "2019", "Nintendo", "Mario Bros", 19);


INSERT INTO genre (genre_name, genre_type)
VALUES("Top-Down Shooter", "Shooter");

INSERT INTO genre (genre_name, genre_type)
VALUES("Platformer", "Action");

INSERT INTO genre (genre_name, genre_type)
VALUES("Role-Playing Game", "RPG");

INSERT INTO genre (genre_name, genre_type)
VALUES("Metroidvania", "Adventure");

INSERT INTO genre (genre_name, genre_type)
VALUES("First-Person Shooter", "Shooter");

INSERT INTO genre (genre_name, genre_type)
VALUES("3rd-Person Adventure", "Adventure");

INSERT INTO genre (genre_name, genre_type)
VALUES("Tactical Stealth", "Action");

INSERT INTO genre (genre_name, genre_type)
VALUES("Puzzle Platformer", "Puzzle");

INSERT INTO genre (genre_name, genre_type)
VALUES("3rd Person Shooter", "Shooter");

INSERT INTO genre (genre_name, genre_type)
VALUES("Multiplayer Arena Shooter", "Shooter");


INSERT INTO game_genre (game_id, genre_id)
VALUES (1, 1);

INSERT INTO game_genre (game_id, genre_id)
VALUES (2, 2);

INSERT INTO game_genre (game_id, genre_id)
VALUES (3, 3);

INSERT INTO game_genre (game_id, genre_id)
VALUES (4, 4);

INSERT INTO game_genre (game_id, genre_id)
VALUES (5, 5);

INSERT INTO game_genre (game_id, genre_id)
VALUES (5, 10);

INSERT INTO game_genre (game_id, genre_id)
VALUES (6, 4);

INSERT INTO game_genre (game_id, genre_id)
VALUES (7, 6);

INSERT INTO game_genre (game_id, genre_id)
VALUES (8, 3);

INSERT INTO game_genre (game_id, genre_id)
VALUES (9, 7);

INSERT INTO game_genre (game_id, genre_id)
VALUES (9, 9);

INSERT INTO game_genre (game_id, genre_id)
VALUES (10, 8);

INSERT INTO game_genre (game_id, genre_id)
VALUES (11, 6);

INSERT INTO game_genre (game_id, genre_id)
VALUES (12, 2);

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (1, 1, 6, "SO STRESSFUL! Pretty decent for an early shooter, but DO NOT stay in any room too long.");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (2, 2, 9, "My favorite game as a child, and it still holds up! And that moon music...");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (3, 3, 10, "The example I still hold JRPGs up to to this day. Phenomenal characters, writing, and unsurpassed music.");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (4, 4, 10, "Holy cow this game awakened in me a love for exploration-type adventure games. And it deserves its place in the genre name.");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (5, 5, 8, "Not the best FPS by any stretch, but I do not think I had as much fun with any other FPS. Great and varied campaign, but the multiplayer is unmatched.");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (6, 6, 10, "When most games on the Playstation were ugly 3d messes, this one went 2d and made it look as good as anything. And honestly, the inverted castle blew all of our little minds.");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (7, 7, 9, "Wind Waker was divisive from day one, but it has aged better than most Zelda games before it or since. So beautiful, and the frozen castle still haunts me.");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (8, 8, 10, "Nintendo kind of screwed themselves peaking with the second Paper Mario game. Super Paper Mario was great for what it was, but fans have wished ever PM game since was this one.");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (9, 9, 8, "This was Kojima at his peak. The storytelling here is so perfect you can totally look past the few frustrations you encounter along the way.");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (10, 10, 9, "This game was like a punch in the gut. I don't think a game has left me as philosophically shattered as this one. Damn you Jonathan Blow!");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (11, 11, 10, "This was the first Dark Souls game I beat, and I have wished every DS game since could be more like this one. The map structure is so great, and the first time you see Anor Londo is just stunning!");

INSERT INTO review (review_id, game_id, review_score, review_text)
VALUES (12, 12, 10, "This is the Mario game I've wanted all my life. I would give it an 11 if they kept adding content.");