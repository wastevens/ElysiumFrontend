
insert into User(id, email, password) values (2, 'admin@ut.org', '6f5be8fc9cabab316797482174fa6a5d3c2f0faee9e8d133a85514a5f15d67bc4f45343aa76062c7');
insert into User(id, email, password) values (3, 'patron@ut.org', '98cb5da4d5c8b11e1155d7bdd21538bf9afba0e6711d7a2093aceec7102df71768045e07e254cf30');
insert into User(id, email, password) values (4, 'expired-patron@ut.org', '4ab23102f8d466c1b6a0a3f6ae6072c0cbdf7cef1336110ccc48706e7f0efd01d83160194fc3cb3b');
insert into User(id, email, password) values (5, 'user@ut.org', '9f08938d6e18d1624e1d47744ab26a458dea6cb24e52d6d40df10b5b2c835628f773e05fdb2c7950');

insert into Patronage(id, year, expiration, original_username) values (42, 2014, '2020-12-31', null);
insert into Patronage(id, year, expiration, original_username) values (43, 2014, '2016-12-31', 'old database username 1');
insert into Patronage(id, year, expiration, original_username) values (44, 2014, '2014-12-31', 'old database username 2');
insert into Patronage(id, year, expiration, original_username) values (45, 2014, '2020-12-31', null);

insert into Patronage_payments(Patronage_id, payment_amount, payment_date, payment_receipt_identifier, payment_type, order_by) values (42, 0.00, '2014-02-01', 'receipt id 1', 3, 0);
insert into Patronage_payments(Patronage_id, payment_amount, payment_date, payment_receipt_identifier, payment_type, order_by) values (43, 20.00, '2014-02-01', '', 1, 0);
insert into Patronage_payments(Patronage_id, payment_amount, payment_date, payment_receipt_identifier, payment_type, order_by) values (43, 40.00, '2015-02-01', 'receipt id 2', 1, 1);
insert into Patronage_payments(Patronage_id, payment_amount, payment_date, payment_receipt_identifier, payment_type, order_by) values (44, 99.99, '2014-02-01', '', 1, 0);

insert into User_roles(user_id, roles) values (2, 0);
insert into User_roles(user_id, roles) values (2, 1);
insert into User_roles(user_id, roles) values (3, 1);
insert into User_roles(user_id, roles) values (4, 1);
insert into User_roles(user_id, roles) values (5, 1);

insert into Troupe(id, name, venue) values(1, 'Cam Troupe', 0);
insert into Troupe(id, name, venue) values(2, 'Anarch Troupe', 0);
insert into Troupe(id, name, venue) values(3, 'Sabbat Troupe', 1);
	
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('troupe', 50);
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('user', 50);
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('patronage', 50);
