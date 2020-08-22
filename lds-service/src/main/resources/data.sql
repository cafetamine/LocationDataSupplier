insert into LOCATION_COORDINATES (Latitude, Longitude, Altitude)
     values ('10.123456', '10.123456', '10.123456'),
            ('11.123456', '11.123456', '11.123456'),
            ('12.123456', '12.123456', '12.123456');

insert into LOCATION_ADDRESS (Country, State, City, Street, Number, PostalCode)
     values ('Country1', 'State1', 'City1', 'Street1', 'Number1', 'PostalCode1'),
            ('Country2', 'State2', 'City2', 'Street2', 'Number2', 'PostalCode2'),
            ('Country3', 'State3', 'City3', 'Street3', 'Number3', 'PostalCode3');

insert into LOCATION_STATUS (Status, LastReadDateTime, NextReadDatetime)
     values ('Unavailable', '2020-08-22T01:50:00', '2020-08-22T02:00:00'),
            ('Available',   '2020-08-22T01:49:00', '2020-08-22T01:59:00'),
            ('Unknown',     null,                  '2020-08-22T02:00:00');
