-- some user inserts, they have also some activities running
INSERT INTO sky_user (first_name, last_name, email, current_timer_running, timer_start_time, started_sport, points, office, user_password) VALUES
    ('Alice', 'Johnson', 'alice.johnson@sky.uk', FALSE, NULL, NULL, 15, 0, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Mark', 'Wilson', 'mark.wilson@sky.uk', TRUE, '2024-10-01 09:30:00', 'Running', 25, 1, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Sara', 'Davis', 'sara.davis@sky.uk', FALSE, NULL, NULL, 30, 2, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Michael', 'Brown', 'michael.brown@sky.uk', TRUE, '2024-10-01 10:00:00', 'Cycling', 10, 3, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Emily', 'Taylor', 'emily.taylor@sky.uk', FALSE, NULL, NULL, 5, 0, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('David', 'Anderson', 'david.anderson@sky.uk', TRUE, '2024-10-01 11:15:00', 'Swimming', 20, 1, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Laura', 'Thomas', 'laura.thomas@sky.uk', FALSE, NULL, NULL, 12, 2, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('James', 'Jackson', 'james.jackson@sky.uk', TRUE, '2024-10-01 12:30:00', 'Basketball', 18, 3, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Sophia', 'White', 'sophia.white@sky.uk', FALSE, NULL, NULL, 22, 0, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Daniel', 'Harris', 'daniel.harris@sky.uk', FALSE, NULL, NULL, 28, 1, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Olivia', 'Martin', 'olivia.martin@sky.uk', TRUE, '2024-10-01 13:45:00', 'Tennis', 14, 2, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Benjamin', 'Lee', 'benjamin.lee@sky.uk', FALSE, NULL, NULL, 9, 3, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Mia', 'Walker', 'mia.walker@sky.uk', TRUE, '2024-10-01 14:50:00', 'Yoga', 11, 0, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Liam', 'Hall', 'liam.hall@sky.uk', FALSE, NULL, NULL, 17, 1, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW'),
    ('Ava', 'Allen', 'ava.allen@sky.uk', TRUE, '2024-10-01 15:30:00', 'Soccer', 13, 2, '$2a$12$PMaXXn0TUEgR0/RvuVjD3uKAq8tYT0B0RGsdyBJzFSTwkNps5VTqW');

-- the list of sports - the ppm specifies the actual calories/m
INSERT INTO sport (name, ppm) VALUES
    ('Running', 10.5),
    ('Cycling', 8.75),
    ('Swimming', 7.25),
    ('Basketball', 12.3),
    ('Tennis', 9.5),
    ('Yoga', 5.0),
    ('Soccer', 11.1),
    ('Weightlifting', 15.2),
    ('Golf', 4.5),
    ('Boxing', 13.75);

-- insert some teams - this is what we need to add for a team
INSERT INTO team (name, imageURL, description, date_created, created_by_id) VALUES
    ('The Champions', '/images/team.jpg', 'A team of champions competing for the top spot.', '2015-09-09 20:22:15', 1),
    ('The Mavericks', '/images/demonlogo.jpeg', 'Innovative thinkers breaking the mold.', '2016-01-15 14:22:30', 2),
    ('The Warriors', '/images/kitsunelogo.jpg', 'Fierce competitors with a strong bond.', '2017-03-12 18:45:00', 3),
    ('The Dream Team', '/images/lionlogo.jpeg', 'A legendary group with dreams of victory.', '2018-07-22 10:10:10', 1),
    ('The Explorers', '/images/logo192.png', 'Adventurers seeking new challenges.', '2019-11-05 09:30:45', 2),
    ('The Guardians', '/images/logo512.png', 'Protectors of the realm, united as one.', '2020-02-20 16:50:00', 5),
    ('The Innovators', '/images/sky.png', 'Pioneers of new ideas and strategies.', '2021-05-10 11:20:30', 4),
    ('The Gladiators', '/images/skyStrideBlueLogo.png', 'Brave warriors ready for battle.', '2022-08-18 17:55:15', 1),
    ('The Titans', '/images/skyStridePurpleLogo.png', 'Colossal players dominating the field.', '2023-01-01 12:00:00', 2),
    ('The Legends', '/images/team.jpg', 'Creating a legacy that will be remembered.', '2023-03-15 15:30:00', 7);

-- assign users into teams - do not add the creator to the team
INSERT INTO team_members (team_id, skyuser_id) VALUES
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),

    (2, 6),
    (2, 7),
    (2, 8),

    -- (3, 1),
    (3, 2),
    (3, 6),

    (4, 3),
    (4, 4),
    (4, 5),
    (4, 9),

    -- (5, 1),
    -- (5, 3),
    (5, 7),
    (5, 10),

    (6, 4),
    (6, 8),

    (7, 2),
    (7, 6),
    (7, 9),

    (8, 3),
    (8, 7),
    (8, 8),

    -- (9, 5),
    (9, 10),

    -- (10, 2),
    -- (10, 4),
    (10, 6),
    (10, 8);

-- add some activities for users
INSERT INTO activity (sky_user_id, sport_id, start_time, end_time, points_earned) VALUES
    (1, 2, '2016-01-10 08:00:00', '2016-01-10 09:30:00', 30),
    (1, 4, '2017-04-15 10:00:00', '2017-04-15 12:00:00', 50),
    (1, 3, '2018-05-20 10:30:00', '2018-05-20 12:00:00', 20),

    (2, 3, '2017-03-15 14:00:00', '2017-03-15 16:00:00', 50),
    (2, 1, '2018-06-18 09:00:00', '2018-06-18 11:00:00', 25),
    (2, 4, '2019-07-25 18:00:00', '2019-07-25 20:00:00', 45),

    (3, 1, '2018-05-20 10:30:00', '2018-05-20 12:00:00', 20),
    (3, 2, '2019-08-30 14:00:00', '2019-08-30 15:30:00', 30),
    (3, 4, '2020-09-10 16:00:00', '2020-09-10 18:00:00', 40),

    (4, 4, '2019-07-25 18:00:00', '2019-07-25 20:00:00', 45),
    (4, 2, '2021-10-05 09:30:00', '2021-10-05 11:00:00', 35),
    (4, 5, '2022-11-20 13:00:00', '2022-11-20 15:00:00', 55),

    (5, 2, '2020-02-05 09:00:00', '2020-02-05 10:30:00', 35),
    (5, 1, '2021-03-15 14:00:00', '2021-03-15 15:30:00', 40),
    (5, 3, '2022-12-10 17:00:00', '2022-12-10 19:00:00', 60),

    (6, 5, '2021-06-30 15:00:00', '2021-06-30 17:00:00', 60),
    (6, 4, '2022-08-18 10:00:00', '2022-08-18 12:30:00', 50),

    (7, 3, '2022-09-18 12:00:00', '2022-09-18 14:00:00', 25),
    (7, 1, '2023-02-12 09:00:00', '2023-02-12 10:30:00', 30),

    (8, 4, '2023-01-12 07:00:00', '2023-01-12 08:30:00', 40),
    (8, 2, '2023-03-10 11:00:00', '2023-03-10 12:45:00', 55),

    (9, 2, '2023-03-10 11:00:00', '2023-03-10 12:45:00', 55),
    (9, 4, '2023-04-15 16:00:00', '2023-04-15 17:30:00', 45),

    (10, 1, '2023-05-15 19:00:00', '2023-05-15 20:30:00', 30),
    (10, 3, '2023-06-20 15:00:00', '2023-06-20 17:00:00', 70);

-- insert many achievements
INSERT INTO achievement (name, img, points_needed) VALUES
    ('Boxing', '/images/boxing-badge.png', 200),
    ('Cycling', '/images/cycling-badge.png', 300),
    ('Football', '/images/football-badge.png', 400),
    ('Runner', '/images/novice-runner.png', 500),
    ('Rackets', '/images/racketssports-badge.png', 600),
    ('Running', '/images/running-badge.png', 700),
    ('Strength', '/images/strength-training-badge.png', 250),
    ('Swimming', '/images/swimming-badge.png', 350),
    ('Walking', '/images/walking-badge.png', 450),
    ('Yoga', '/images/yoga-badge.png', 550);

