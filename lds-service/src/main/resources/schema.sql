drop table if exists LOCATION;
drop table if exists ADDRESS;

create table LOCATION_COORDINATES(
  Id bigint auto_increment primary key,
  Latitude varchar(25) not null,
  Longitude varchar(25) not null,
  Altitude varchar(25)
);

create table LOCATION_ADDRESS(
  Id bigint auto_increment primary key,
  Country varchar(120) not null,
  State varchar(255) not null,
  City varchar(255) not null,
  Street varchar(255) not null,
  Number varchar(25) not null,
  PostalCode varchar(25) not null
);
