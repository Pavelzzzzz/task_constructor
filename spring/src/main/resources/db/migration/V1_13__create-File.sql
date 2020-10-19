CREATE TABLE IF NOT EXISTS File (
  Id INT NOT NULL AUTO_INCREMENT,
  Name VARCHAR(255),
  Type VARCHAR(255),
  data longblob,
  CreatedAt DATETIME NOT NULL,
  PRIMARY KEY (Id));
