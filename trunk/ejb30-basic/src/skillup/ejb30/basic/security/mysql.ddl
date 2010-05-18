create table users(
	username varchar(50),
	password varchar(500),
	mail_addr varchar(200),
	primary key (username)
);

create table roles(
	rolename varchar(50),
	username varchar(50),
	primary key (rolename, username)
);

insert into users values ('akira', 'milan', null);
insert into users values ('milan', 'akira', null);
insert into users values ('jizai', 'milan+akira', null);

insert into roles values ('admin', 'akira');
insert into roles values ('admin', 'jizai');
insert into roles values ('developer', 'akira');
insert into roles values ('developer', 'jizai');
insert into roles values ('cunsumer', 'jizai');
insert into roles values ('cunsumer', 'milan');