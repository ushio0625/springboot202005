CREATE TABLE IF NOT EXISTS person1 (
  id SERIAL NOT NULL,
  name VARCHAR(256) NOT NULL,
  created_at TIMESTAMP,
  PRIMARY KEY (id)
);