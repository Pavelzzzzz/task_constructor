CREATE TABLE IF NOT EXISTS JSONExport (
  Id INT NOT NULL AUTO_INCREMENT,
  Title VARCHAR(255) NOT NULL,
  Body VARCHAR(10299),
  CreatedAt DATETIME NOT NULL,
  PRIMARY KEY (Id));
