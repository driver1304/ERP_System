insert into roles(name) value ('ROLE_ADMIN');
insert into roles(name) value ('ROLE_USER');


insert into companies(name) value ('Etgar');
insert into companies(name) value ('Etgar');

insert into users(email, first_name,last_name,password,company_id) values ('szymek.fafrowicz@gmail.com','Szymon','Fąfrowicz','$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6',1);
insert into users(email, first_name,last_name,password,company_id) values ('szymek@gmail.com','Szymon','Fąfrowicz','$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6',2);
insert into users(email, first_name,last_name,password,company_id) values ('user@gmail.com','Tomek','User','$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6',1);
insert into users(email, first_name,last_name,password,company_id) values ('user2@gmail.com','Jan','User2','$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6',2);


insert into users_roles(user_id,role_id) values (1,1);
insert into users_roles(user_id,role_id) values (2,1);
insert into users_roles(user_id,role_id) values (3,2);
insert into users_roles(user_id,role_id) values (4,2);

