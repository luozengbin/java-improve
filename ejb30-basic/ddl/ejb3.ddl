
create table catalog
(
	id SERIAL,
	name varchar(255),
	comment varchar(255),
	create_dt timestamp,
	update_dt timestamp,
	primary key(id)
)

create table maker(
	id SERIAL,
	name varchar(255),
	location varchar(10),
	sence timestamp,
	primary key(id)
)

create table product
(
	id SERIAL,
	name varchar(255),
	catalog_id BIGINT,
	comment varchar(2000),
	maker_id BIGINT,
	make_dt timestamp,
	create_dt timestamp,
	update_dt timestamp,
	primary key(id),
	FOREIGN KEY (catalog_id) REFERENCES catalog (id),
	FOREIGN KEY (maker_id) REFERENCES maker
)