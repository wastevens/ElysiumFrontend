
insert into Patronage(id, year, expiration) values (42, 2014, '2020-12-31');
insert into Patronage(id, year, expiration) values (43, 2014, '2020-12-31');
insert into Patronage(id, year, expiration) values (44, 2014, '2014-12-31');
	
insert into User(id, email, password, patronage_id) values (2, 'admin@ut.org', 'password', 42);
insert into User(id, email, password, patronage_id) values (3, 'patron@ut.org', 'password', 43);
insert into User(id, email, password, patronage_id) values (4, 'expired-patron@ut.org', 'password', 44);
insert into User(id, email, password, patronage_id) values (5, 'user@ut.org', 'password', null);
	
insert into User_roles(user_id, roles) values (2, 0);
insert into User_roles(user_id, roles) values (2, 1);
insert into User_roles(user_id, roles) values (3, 1);
insert into User_roles(user_id, roles) values (4, 1);
insert into User_roles(user_id, roles) values (5, 1);

insert into Troupe(id, name, setting) values(1, 'Cam Troupe', 0);
insert into Troupe(id, name, setting) values(2, 'Anarch Troupe', 1);
insert into Troupe(id, name, setting) values(3, 'Sabbat Troupe', 2);
	
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('troupe', 50);
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('user', 50);
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('patronage', 50);
