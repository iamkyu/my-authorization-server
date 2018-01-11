CREATE TABLE IF NOT EXISTS user (
  no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(256) UNIQUE NOT NULL ,
  password VARCHAR(256) NOT NULL
);



CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  member_no VARCHAR(256),
  refresh_token VARCHAR(256)
);
