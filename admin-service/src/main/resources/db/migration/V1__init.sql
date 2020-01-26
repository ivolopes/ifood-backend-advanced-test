--Usuario

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  fullname VARCHAR(250) NOT NULL,
  username VARCHAR(100) NOT NULL unique,
  password VARCHAR(250) NOT NULL,
  active INT NOT NULL
);

INSERT INTO user (fullname, username, password, active) VALUES
  ('Ivo Lopes de Oliveira', 'ivolopes', '$2a$10$ukLREsh/0nzoKFq/zAbqX.Pn161RMJcVV3QH7MORwaNmQzuBENEai',1);
