-- some user inserts, they have also some activities running
INSERT INTO sky_user (first_name, last_name, email, current_timer_running, timer_start_time, started_sport, points, office) VALUES
    ('Alice', 'Johnson', 'alice.johnson@sky.uk', FALSE, NULL, NULL, 15, 0),
    ('Mark', 'Wilson', 'mark.wilson@sky.uk', TRUE, '2024-10-01T09:30:00', 'Running', 25, 1),
    ('Sara', 'Davis', 'sara.davis@sky.uk', FALSE, NULL, NULL, 30, 2),
    ('Michael', 'Brown', 'michael.brown@sky.uk', TRUE, '2024-10-01T10:00:00', 'Cycling', 10, 3),
    ('Emily', 'Taylor', 'emily.taylor@sky.uk', FALSE, NULL, NULL, 5, 0),
    ('David', 'Anderson', 'david.anderson@sky.uk', TRUE, '2024-10-01T11:15:00', 'Swimming', 20, 1),
    ('Laura', 'Thomas', 'laura.thomas@sky.uk', FALSE, NULL, NULL, 12, 2),
    ('James', 'Jackson', 'james.jackson@sky.uk', TRUE, '2024-10-01T12:30:00', 'Basketball', 18, 3),
    ('Sophia', 'White', 'sophia.white@sky.uk', FALSE, NULL, NULL, 22, 0),
    ('Daniel', 'Harris', 'daniel.harris@sky.uk', FALSE, NULL, NULL, 28, 1),
    ('Olivia', 'Martin', 'olivia.martin@sky.uk', TRUE, '2024-10-01T13:45:00', 'Tennis', 14, 2),
    ('Benjamin', 'Lee', 'benjamin.lee@sky.uk', FALSE, NULL, NULL, 9, 3),
    ('Mia', 'Walker', 'mia.walker@sky.uk', TRUE, '2024-10-01T14:50:00', 'Yoga', 11, 0),
    ('Liam', 'Hall', 'liam.hall@sky.uk', FALSE, NULL, NULL, 17, 1),
    ('Ava', 'Allen', 'ava.allen@sky.uk', TRUE, '2024-10-01T15:30:00', 'Soccer', 13, 2);


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
    (1, 2, '2016-01-10T08:00:00', '2016-01-10T09:30:00', 30),
    (1, 4, '2017-04-15T10:00:00', '2017-04-15T12:00:00', 50),
    (1, 3, '2018-05-20T10:30:00', '2018-05-20T12:00:00', 20),
    
    (2, 3, '2017-03-15T14:00:00', '2017-03-15T16:00:00', 50),
    (2, 1, '2018-06-18T09:00:00', '2018-06-18T11:00:00', 25),
    (2, 4, '2019-07-25T18:00:00', '2019-07-25T20:00:00', 45),
    
    (3, 1, '2018-05-20T10:30:00', '2018-05-20T12:00:00', 20),
    (3, 2, '2019-08-30T14:00:00', '2019-08-30T15:30:00', 30),
    (3, 4, '2020-09-10T16:00:00', '2020-09-10T18:00:00', 40),
    
    (4, 4, '2019-07-25T18:00:00', '2019-07-25T20:00:00', 45),
    (4, 2, '2021-10-05T09:30:00', '2021-10-05T11:00:00', 35),
    (4, 5, '2022-11-20T13:00:00', '2022-11-20T15:00:00', 55),
    
    (5, 2, '2020-02-05T09:00:00', '2020-02-05T10:30:00', 35),
    (5, 1, '2021-03-15T14:00:00', '2021-03-15T15:30:00', 40),
    (5, 3, '2022-12-10T17:00:00', '2022-12-10T19:00:00', 60),
    
    (6, 5, '2021-06-30T15:00:00', '2021-06-30T17:00:00', 60),
    (6, 4, '2022-08-18T10:00:00', '2022-08-18T12:30:00', 50),
    
    (7, 3, '2022-09-18T12:00:00', '2022-09-18T14:00:00', 25),
    (7, 1, '2023-02-12T09:00:00', '2023-02-12T10:30:00', 30),
    
    (8, 4, '2023-01-12T07:00:00', '2023-01-12T08:30:00', 40),
    (8, 2, '2023-03-10T11:00:00', '2023-03-10T12:45:00', 55),
    
    (9, 2, '2023-03-10T11:00:00', '2023-03-10T12:45:00', 55),
    (9, 4, '2023-04-15T16:00:00', '2023-04-15T17:30:00', 45),
    
    (10, 1, '2023-05-15T19:00:00', '2023-05-15T20:30:00', 30),
    (10, 3, '2023-06-20T15:00:00', '2023-06-20T17:00:00', 70);

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

