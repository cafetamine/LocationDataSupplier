drop table if exists LOCATION_COORDINATES;
drop table if exists LOCATION_ADDRESS;

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

create table LOCATION_STATUS(
  Id bigint auto_increment primary key,
  Status varchar(25) not null,
  LastReadDateTime datetime2,
  NextReadDatetime datetime2 not null
);
