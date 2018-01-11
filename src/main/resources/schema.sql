CREATE TABLE IF NOT EXISTS user (
  no INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(256) UNIQUE NOT NULL,
  password VARCHAR(256) NOT NULL
);

create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create table oauth_client_token (
  token_id VARCHAR(256),
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

create table oauth_access_token (
  token_id VARCHAR(256),
  authentication_id VARCHAR(256) PRIMARY KEY,
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
