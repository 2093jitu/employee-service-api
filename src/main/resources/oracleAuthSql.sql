
--------------- Oracle ---------------
CREATE TABLE OAUTH_CLIENT_DETAILS
(
  CLIENT_ID                VARCHAR2(255 BYTE),
  RESOURCE_IDS             VARCHAR2(255 BYTE),
  CLIENT_SECRET            VARCHAR2(255 BYTE),
  SCOPE                    VARCHAR2(255 BYTE),
  AUTHORIZED_GRANT_TYPES   VARCHAR2(255 BYTE),
  WEB_SERVER_REDIRECT_URI  VARCHAR2(255 BYTE),
  AUTHORITIES              VARCHAR2(255 BYTE),
  ACCESS_TOKEN_VALIDITY    INTEGER,
  REFRESH_TOKEN_VALIDITY   INTEGER,
  ADDITIONAL_INFORMATION   VARCHAR2(4000 BYTE),
  AUTOAPPROVE              VARCHAR2(255 BYTE)
)

CREATE TABLE OAUTH_CLIENT_TOKEN
(
  TOKEN_ID           VARCHAR2(255 BYTE),
  TOKEN              BLOB,
  AUTHENTICATION_ID  VARCHAR2(255 BYTE),
  USER_NAME          VARCHAR2(255 BYTE),
  CLIENT_ID          VARCHAR2(255 BYTE)
)

CREATE TABLE OAUTH_ACCESS_TOKEN
(
  TOKEN_ID           VARCHAR2(255 BYTE),
  TOKEN              BLOB,
  AUTHENTICATION_ID  VARCHAR2(255 BYTE),
  USER_NAME          VARCHAR2(255 BYTE),
  CLIENT_ID          VARCHAR2(255 BYTE),
  AUTHENTICATION     BLOB,
  REFRESH_TOKEN      VARCHAR2(255 BYTE)
)

CREATE TABLE OAUTH_REFRESH_TOKEN
(
  TOKEN_ID        VARCHAR2(255 BYTE),
  TOKEN           BLOB,
  AUTHENTICATION  BLOB
)

CREATE TABLE OAUTH_CODE
(
  CODE            VARCHAR2(255 BYTE),
  AUTHENTICATION  BLOB
)


CREATE TABLE OAUTH_APPROVALS
(
  USERID          VARCHAR2(255 BYTE),
  CLIENTID        VARCHAR2(255 BYTE),
  SCOPE           VARCHAR2(255 BYTE),
  STATUS          VARCHAR2(10 BYTE),
  EXPIRESAT       TIMESTAMP(6),
  LASTMODIFIEDAT  TIMESTAMP(6)
)

--------------Data-----------------------------
INSERT INTO oauth_client_details 
	(client_id, client_secret, scope, authorized_grant_types, access_token_validity,
	refresh_token_validity, additional_information, autoapprove) 
VALUES
	('medClientIdPassword', '$2a$10$oLsTpxYoRXlpBaOJ8djYeOZiItQIxbZBEQFAwxjgvowYA7R.8Aw7K', 
	'med,read,write', 'password,authorization_code,refresh_token', 36000, 36000, null, true);
--------------- MySQL ---------------
drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

create table if not exists oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table if not exists oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

create table if not exists oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

create table if not exists oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);

create table if not exists oauth_approvals (
	userId VARCHAR(255),
	clientId VARCHAR(255),
	scope VARCHAR(255),
	status VARCHAR(10),
	expiresAt TIMESTAMP,
	lastModifiedAt TIMESTAMP
);
--------------Data-----------------------------
INSERT INTO oauth_client_details 
	(client_id, client_secret, scope, authorized_grant_types, access_token_validity,
	refresh_token_validity, additional_information, autoapprove) 
VALUES
	('medClientIdPassword', '$2a$10$oLsTpxYoRXlpBaOJ8djYeOZiItQIxbZBEQFAwxjgvowYA7R.8Aw7K', 
	'med,read,write', 'password,authorization_code,refresh_token', 36000, 36000, null, true);
	
INSERT INTO core_user (id, account_expire_date, account_expired, account_locked, activeStatus, agent_id, company_id,
company_name, created_by, date_created, dateOfBirth, email, enabled, full_name, gender, password, password_expired, phone, USER_NAME) 
VALUES
('4', '2022-01-01', true, true, false, false, false, 'Aalok Health Care Ltd.', 'vendor@gmail.com', '2018-10-24 ', '2018-01-08 ', 'technician@gmail.com', false, 'Abul Kalam', 'Male', '$2a$10$cW/avlQJSHykSEg19T4gQOU4jdOA/bGe95lxWJE82ttwnfu.47LyK', true,'+1022322232', 'technician@gmail.com');
