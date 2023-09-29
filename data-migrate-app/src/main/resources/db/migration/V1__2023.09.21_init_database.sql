CREATE TABLE IF NOT EXISTS Movies
(
    id           INT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    release_year INT,
    genre        VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Actors
(
    id        INT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName  VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS Genres
(
    id   INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_actor
(
    movie_id INT,
    actor_id INT,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES Movies (id),
    FOREIGN KEY (actor_id) REFERENCES Actors (id)
);

CREATE TABLE IF NOT EXISTS movie_genre
(
    movie_id INT,
    genre_id INT,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES Movies (id),
    FOREIGN KEY (genre_id) REFERENCES Actors (id)
);