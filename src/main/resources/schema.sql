CREATE TABLE IF NOT EXISTS user (
  no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(256) UNIQUE NOT NULL,
  password VARCHAR(256) NOT NULL
);

create table oauth_client_details (
  client_id VARCHAR(256),
  client_secret VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  user_no VARCHAR(256),
  client_id VARCHAR(256),
  refresh_token VARCHAR(256)
);

create table oauth_refresh_token (
  token_id VARCHAR(256)
);

create table oauth_approvals (
  user_id VARCHAR(256),
  client_id VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expires_at TIMESTAMP,
  lastModified_at TIMESTAMP
);
