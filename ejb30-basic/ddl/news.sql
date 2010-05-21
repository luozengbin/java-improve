drop table news;

create table news(
	id SERIAL,
	title varchar(2500),
	content clob,
	publishDt timestamp,
	publisherId varchar(255),
	primary key(id)
)