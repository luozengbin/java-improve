drop table GeneratedID;
drop table GenerationBall;

create table GeneratedID(
	pk_column varchar(255), 
	pk_value int
);
create table GenerationBall(
	ballId int,
	comments varchar(1000),
	primary key (ballId)
);