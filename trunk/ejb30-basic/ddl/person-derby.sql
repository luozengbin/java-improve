drop table person;

create table person(
	id integer generated always as identity (start with 1, increment by 1),
	name varchar(255),
	sex varchar(50),
	age smallint,
	birthday timestamp,
	primary key(id)
);

drop table password;

create table password(
	pid integer generated always as identity (start with 1, increment by 1),
	password varchar(500),
	primary key(pid)
)