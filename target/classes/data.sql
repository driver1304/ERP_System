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


insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt budowlany domek jednorodzinny', 'Wykonać projekt budlowany instalacji sanitarnych. Lokalizacja: ul. Długa 2, Kraków.', 100, '2020-10-10', '2020-12-24', false,1);
insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt budowlany bud. użyt. publicznej', 'Wykonać projekt budlowany instalacji went-klim. Lokalizacja: ul. Krótka 12, Kraków.', 200, '2020-11-10', '2020-12-28', true,1);
insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt wykonawczy bud. jednorodzinnych', 'Wykonać projekt wykonawczy instalacji sanitarnych dla osiedla domków jednorodzinnych. Lokalizacja: ul. os. Podwawelskie 42, Kraków.', 200, '2020-11-10', '2020-12-28', true,2);
insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt wykonawczy domek jednorodzinny', 'Wykonać projekt budlowany instalacji sanitarnych. Lokalizacja: ul. Długa 2, Kraków.', 200, '2020-10-10', '2020-12-31', false,1);
insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt budowalny sieć wodociągowa', 'Wykonać projekt budlowany sieci wod. dla dz. Dębniki w Krakowie.', 200, '2021-01-05', '2021-03-31', false,1);
insert into tasks(name, description, hours_budget, start_term, deadline, completed,company_id) values ('Projekt wykonawczy sieć wodociągowa', 'Wykonać projekt wykonawczy sieci wod. dla dz. Dębniki w Krakowie.', 250, '2021-04-10', '2021-08-20', false,1);

insert into user_task_hours_budget(user_id, task_id,hours_budget, description) values (3, 1, 30, 'Wykonać projekt instalacji kanalizacyjnej.' );
insert into user_task_hours_budget(user_id, task_id,hours_budget,description) values (3, 4, 50, 'Wykonać projekt instalacji kanalizacyjnej.');
insert into user_task_hours_budget(user_id, task_id,hours_budget,description) values (3, 5, 60, 'Wykonać projekt bud. sieci wociągowej.');
insert into user_task_hours_budget(user_id, task_id,hours_budget,description) values (3, 6, 65, 'Wykonać projekt wyk. oraz prowadzić nadzory.');
insert into user_task_hours_budget(user_id, task_id,hours_budget,description) values (3, 2, 45, 'Wykonać projekt instalacji wentylacyjnej.');
insert into user_task_hours_budget(user_id, task_id,hours_budget,description) values (6, 1, 10,'Wykonać projekt instalacji wodociągowej.');
insert into user_task_hours_budget(user_id, task_id,hours_budget,description) values (7, 2, 10, 'Wykonać projekt instalacji klimatyzacyjnej.');
insert into user_task_hours_budget(user_id, task_id,hours_budget,description) values (4, 3, 40, 'Wykonać projekt instalacji kanalizacyjnej.');
