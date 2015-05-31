
insert into User(id, email, password) values (2, 'admin@ut.org', 'password');
insert into User(id, email, password) values (3, 'patron@ut.org', 'password');
insert into User(id, email, password) values (4, 'expired-patron@ut.org', 'password');
insert into User(id, email, password) values (5, 'user@ut.org', 'password');

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
