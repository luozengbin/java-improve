drop table person;

create table person(
	id SERIAL,
	name varchar(255),
	sex varchar(50),
	age smallint,
	birthday timestamp,
	primary key(id)
);

drop table password;

create table password(
	pid SERIAL,
	password varchar(500),
	primary key(pid)
)