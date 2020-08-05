drop table if exists LOCATION;

create table LOCATION(
  Id bigint auto_increment primary key ,
  Latitude varchar(25) not null,
  Longitude varchar(25) not null,
  Altitude varchar(25)
);
