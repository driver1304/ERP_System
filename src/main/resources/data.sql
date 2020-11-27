insert into roles(name) value ('ROLE_ADMIN');
insert into roles(name) value ('ROLE_USER');


insert into companies(name) value ('Etgar');
insert into companies(name) value ('Etgar');

insert into users(email, first_name, last_name, password, company_id) values ('szymek.fafrowicz@gmail.com', 'Szymon', 'Fąfrowicz','$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 1);
insert into users(email, first_name, last_name, password, company_id) values ('szymek@gmail.com', 'Szymon', 'Fąfrowicz', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 2);
insert into users(email, first_name, last_name, password, company_id) values ('user@gmail.com', 'Tomek', 'User', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 1);
insert into users(email, first_name, last_name, password, company_id) values ('user2@gmail.com', 'Jan', 'User2', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 2);


insert into users_roles(user_id, role_id) values (1, 1);
insert into users_roles(user_id, role_id) values (2, 1);
insert into users_roles(user_id, role_id) values (3, 2);
insert into users_roles(user_id, role_id) values (4, 2);


insert into tasks(name, description, hours_budget, start_term, deadline, completed) values ('Zad1', 'Description1', 100, '2020-10-10', '2020-12-24', false);
insert into tasks(name, description, hours_budget, start_term, deadline, completed) values ('Zad2', 'Description2', 200, '2020-11-10', '2020-12-28', true);
insert into tasks(name, description, hours_budget, start_term, deadline, completed) values ('Zad3', 'Description3', 200, '2020-11-10', '2020-12-28', true);

insert into user_task_hours_budget(user_id, task_id,hours_budget) values (3, 1, 30);
insert into user_task_hours_budget(user_id, task_id,hours_budget) values (3, 2, 45);
insert into user_task_hours_budget(user_id, task_id,hours_budget) values (4, 3, 40);
