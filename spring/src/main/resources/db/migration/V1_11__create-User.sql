CREATE TABLE IF NOT EXISTS User (
  Id INT NOT NULL AUTO_INCREMENT,
  Username VARCHAR(256) NOT NULL,
  isEnabled TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (Id),
  UNIQUE INDEX Username_UNIQUE (Username ASC));
