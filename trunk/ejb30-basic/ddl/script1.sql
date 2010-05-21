ALTER TABLE `database`.`roles` DROP PRIMARY KEY;

ALTER TABLE `database`.`catalog` DROP PRIMARY KEY;

ALTER TABLE `database`.`maker` DROP PRIMARY KEY;

ALTER TABLE `database`.`person` DROP PRIMARY KEY;

ALTER TABLE `database`.`product` DROP PRIMARY KEY;

ALTER TABLE `database`.`users` DROP PRIMARY KEY;

ALTER TABLE `database`.`product` DROP INDEX `database`.`catalog_id`;

ALTER TABLE `database`.`product` DROP INDEX `database`.`id`;

ALTER TABLE `database`.`catalog` DROP INDEX `database`.`id`;

ALTER TABLE `database`.`product` DROP INDEX `database`.`maker_id`;

ALTER TABLE `database`.`maker` DROP INDEX `database`.`id`;

ALTER TABLE `database`.`person` DROP INDEX `database`.`id`;

DROP TABLE `database`.`users`;

DROP TABLE `database`.`product`;

DROP TABLE `database`.`roles`;

DROP TABLE `database`.`person`;

DROP TABLE `database`.`catalog`;

DROP TABLE `database`.`maker`;

CREATE TABLE `database`.`users` (
	`username` VARCHAR(50) NOT NULL,
	`password` VARCHAR(500),
	`mail_addr` VARCHAR(200),
	PRIMARY KEY (`username`)
) ENGINE=MyISAM;

CREATE TABLE `database`.`product` (
	`id` BIGINT UNSIGNED NOT NULL,
	`name` VARCHAR(255),
	`catalog_id` BIGINT,
	`comment` VARCHAR(2000),
	`maker_id` BIGINT,
	`make_dt` TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
	`create_dt` TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
	`update_dt` TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=MyISAM;

CREATE TABLE `database`.`roles` (
	`rolename` VARCHAR(50) NOT NULL,
	`username` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`rolename`,`username`)
) ENGINE=MyISAM;

CREATE TABLE `database`.`person` (
	`id` BIGINT UNSIGNED NOT NULL,
	`name` VARCHAR(255),
	PRIMARY KEY (`id`)
) ENGINE=MyISAM;

CREATE TABLE `database`.`catalog` (
	`id` BIGINT UNSIGNED NOT NULL,
	`name` VARCHAR(255),
	`comment` VARCHAR(255),
	`create_dt` TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
	`update_dt` TIMESTAMP DEFAULT '0000-00-00 00:00:00' NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=MyISAM;

CREATE TABLE `database`.`maker` (
	`id` BIGINT UNSIGNED NOT NULL,
	`name` VARCHAR(255),
	`location` VARCHAR(10),
	`sence` TIMESTAMP DEFAULT 'CURRENT_TIMESTAMP' NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=MyISAM;

CREATE INDEX `catalog_id` ON `database`.`product` (`catalog_id` ASC);

CREATE UNIQUE INDEX `id` ON `database`.`product` (`id` ASC);

CREATE UNIQUE INDEX `id` ON `database`.`catalog` (`id` ASC);

CREATE INDEX `maker_id` ON `database`.`product` (`maker_id` ASC);

CREATE UNIQUE INDEX `id` ON `database`.`maker` (`id` ASC);

CREATE UNIQUE INDEX `id` ON `database`.`person` (`id` ASC);

