insert into roles(name) value ('ROLE_ADMIN');
insert into roles(name) value ('ROLE_USER');


insert into companies(name) value ('Etgar');
insert into companies(name) value ('Budimex');

insert into users(email, first_name, last_name, password, company_id) values ('szymek.fafrowicz@gmail.com', 'Szymon', 'Fąfrowicz','$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 1);
insert into users(email, first_name, last_name, password, company_id) values ('szymek@gmail.com', 'Szymon', 'Dąbrowski', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 2);
insert into users(email, first_name, last_name, password, company_id) values ('user@gmail.com', 'Tomek', 'Młynarczyk', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 1);
insert into users(email, first_name, last_name, password, company_id) values ('user2@gmail.com', 'Jan', 'Hejnał', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 2);
insert into users(email, first_name, last_name, password, company_id) values ('user3@gmail.com', 'Michał', 'Ryba', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 1);
insert into users(email, first_name, last_name, password, company_id) values ('user4@gmail.com', 'Adam', 'Nawałka', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 1);
insert into users(email, first_name, last_name, password, company_id) values ('user5@gmail.com', 'Piotr', 'Kowalski', '$2a$10$lEVSYk0LdQgKViAzQLf45OtX4zB9s5UYgjIzAm..G9cnE5vp4Oro6', 1);


insert into users_roles(user_id, role_id) values (1, 1);
insert into users_roles(user_id, role_id) values (2, 1);
insert into users_roles(user_id, role_id) values (3, 2);
insert into users_roles(user_id, role_id) values (4, 2);
insert into users_roles(user_id, role_id) values (5, 2);
insert into users_roles(user_id, role_id) values (6, 2);
insert into users_roles(user_id, role_id) values (7, 2);


insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt budowlany domek jednorodzinny', 'Description1', 100, '2020-10-10', '2020-12-24', false,1);
insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt budowlany bud. użyt. publicznej', 'Description2', 200, '2020-11-10', '2020-12-28', true,1);
insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt wykonawczy bud. jednorodzinnych', 'Description3', 200, '2020-11-10', '2020-12-28', true,2);

insert into user_task_hours_budget(user_id, task_id,hours_budget) values (3, 1, 30);
insert into user_task_hours_budget(user_id, task_id,hours_budget) values (3, 2, 45);
insert into user_task_hours_budget(user_id, task_id,hours_budget) values (6, 1, 10);
insert into user_task_hours_budget(user_id, task_id,hours_budget) values (7, 2, 10);
insert into user_task_hours_budget(user_id, task_id,hours_budget) values (4, 3, 40);
