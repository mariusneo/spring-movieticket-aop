CREATE TABLE `account`(
	id int(10) unsigned NOT NULL auto_increment,
	amount float not null,
	PRIMARY KEY(id)
);

CREATE TABLE `user`(
	id int(10) unsigned NOT NULL auto_increment,
	name varchar(50) NOT NULL,
	account_id int(10) unsigned NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE `movie_ticket`(
	id int(10) unsigned NOT NULL auto_increment,
	name  varchar(50) NOT NULL,
	tickets_count int(10) unsigned NOT NULL,
	price float not null,
    PRIMARY KEY(id)
);