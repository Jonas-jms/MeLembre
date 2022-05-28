use sql10495760;

CREATE TABLE Users
(
  id INTEGER PRIMARY KEY AUTO_INCREMENT, 
  usuario VARCHAR(50), 
  flagAtivo integer NOT NULL
);

DELETE FROM Users where id=3;

INSERT INTO Users (usuario, flagAtivo) VALUES  ("Magalhães",0);

SELECT * FROM Users;