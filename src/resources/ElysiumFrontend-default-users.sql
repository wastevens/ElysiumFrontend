
insert into User(id, email, password) values (1, 'admin@ut.org', 'password');
insert into User(id, email, password) values (2, 'lst@ut.org', 'password');
insert into User(id, email, password) values (3, 'ast@ut.org', 'password');
insert into User(id, email, password) values (4, 'narrator@ut.org', 'password');
insert into User(id, email, password) values (5, 'patron@ut.org', 'password');
insert into User(id, email, password) values (6, 'user@ut.org', 'password');

insert into User_roles(user_id, roles) values (1, 0);
insert into User_roles(user_id, roles) values (1, 5);

insert into User_roles(user_id, roles) values (2, 1);
insert into User_roles(user_id, roles) values (2, 4);
insert into User_roles(user_id, roles) values (2, 5);

insert into User_roles(user_id, roles) values (3, 2);
insert into User_roles(user_id, roles) values (3, 4);
insert into User_roles(user_id, roles) values (3, 5);

insert into User_roles(user_id, roles) values (4, 3);
insert into User_roles(user_id, roles) values (4, 4);
insert into User_roles(user_id, roles) values (4, 5);

insert into User_roles(user_id, roles) values (5, 4);
insert into User_roles(user_id, roles) values (5, 5);

insert into User_roles(user_id, roles) values (6, 5);

insert into Troupe(id, name, setting) values(1, 'Cam Troupe', 0);
insert into Troupe(id, name, setting) values(2, 'Anarch Troupe', 1);
insert into Troupe(id, name, setting) values(3, 'Sabbat Troupe', 2);
	
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('troupe', 50);
	insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('user', 50);
