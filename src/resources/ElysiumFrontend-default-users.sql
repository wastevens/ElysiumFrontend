
insert into User(id, email, password) values ('admin-id', 'admin@ut.org', 'password');
insert into User(id, email, password) values ('lst-id', 'lst@ut.org', 'password');
insert into User(id, email, password) values ('ast-id', 'ast@ut.org', 'password');
insert into User(id, email, password) values ('narrator-id', 'narrator@ut.org', 'password');
insert into User(id, email, password) values ('patron-id', 'patron@ut.org', 'password');
insert into User(id, email, password) values ('user-id', 'user@ut.org', 'password');

insert into User_roles(user_id, roles) values ('admin-id', 0);
insert into User_roles(user_id, roles) values ('admin-id', 5);

insert into User_roles(user_id, roles) values ('lst-id', 1);
insert into User_roles(user_id, roles) values ('lst-id', 4);
insert into User_roles(user_id, roles) values ('lst-id', 5);

insert into User_roles(user_id, roles) values ('ast-id', 2);
insert into User_roles(user_id, roles) values ('ast-id', 4);
insert into User_roles(user_id, roles) values ('ast-id', 5);

insert into User_roles(user_id, roles) values ('narrator-id', 3);
insert into User_roles(user_id, roles) values ('narrator-id', 4);
insert into User_roles(user_id, roles) values ('narrator-id', 5);

insert into User_roles(user_id, roles) values ('patron-id', 4);
insert into User_roles(user_id, roles) values ('patron-id', 5);

insert into User_roles(user_id, roles) values ('user-id', 5);

insert into Troupe(id, name, setting) values(1, 'Cam Troupe', 0);
insert into Troupe(id, name, setting) values(2, 'Anarch Troupe', 1);
insert into Troupe(id, name, setting) values(3, 'Sabbat Troupe', 2);
	
insert into ID_Sequences(sequence_name, sequence_next_hi_value) values('troupe', 50);
