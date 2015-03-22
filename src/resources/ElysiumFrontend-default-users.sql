
insert into User(id, email, password) values (2, 'admin@ut.org', 'password');
insert into User(id, email, password) values (3, 'lst@ut.org', 'password');
insert into User(id, email, password) values (4, 'ast@ut.org', 'password');
insert into User(id, email, password) values (5, 'narrator@ut.org', 'password');
insert into User(id, email, password) values (6, 'patron@ut.org', 'password');
insert into User(id, email, password) values (7, 'user@ut.org', 'password');

insert into Patronage(id, year, expiration, user_id) values (42, 2014, '2020-12-31', 2);
insert into Patronage(id, year, expiration, user_id) values (43, 2014, '2020-12-31', 3);
insert into Patronage(id, year, expiration, user_id) values (44, 2014, '2020-12-31', 4);
insert into Patronage(id, year, expiration, user_id) values (45, 2014, '2020-12-31', 5);
insert into Patronage(id, year, expiration, user_id) values (46, 2014, '2020-12-31', 6);
insert into Patronage(id, year, expiration, user_id) values (47, 2014, '2020-12-31', null);
	
insert into User_roles(user_id, roles) values (2, 0);
insert into User_roles(user_id, roles) values (2, 1);
insert into User_roles(user_id, roles) values (3, 1);
insert into User_roles(user_id, roles) values (4, 1);
insert into User_roles(user_id, roles) values (5, 1);
insert into User_roles(user_id, roles) values (6, 1);
insert into User_roles(user_id, roles) values (7, 1);

insert into Troupe(id, name, setting) values(1, 'Cam Troupe', 0);
insert into Troupe(id, name, setting) values(2, 'Anarch Troupe', 1);
insert into Troupe(id, name, setting) values(3, 'Sabbat Troupe', 2);
	
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('troupe', 50);
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('user', 50);
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('patronage', 50);
