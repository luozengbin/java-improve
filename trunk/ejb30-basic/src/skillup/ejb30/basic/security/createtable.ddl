create table users(
	username varchar(50),
	password varchar(500),
	mail_addr varchar(200),
	primary key (username)
)

CREATE TABLE ROLES(
	rolename VARCHAR(50),
	username VARCHAR(50),
	comments VARCHAR(1000)
	PRIMARY KEY (rolename,username)
)