CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    userType VARCHAR(50) NOT NULL
);

CREATE TABLE MemberAttributes (
    id INT PRIMARY KEY,
    goal_weight DECIMAL,
    timeline DATE,
    goal_workout VARCHAR(255),
    height DECIMAL,
    weight DECIMAL,
    bf_percentage DECIMAL,
    routineENUM VARCHAR(50),
    paymentstatus VARCHAR(50),
    plan VARCHAR(255),   
    foreign key (id) references Users(id)
);

CREATE TABLE Achievements (
    id INT PRIMARY KEY,
    achievements_user VARCHAR(255),
    foreign key (id) references Users(id)
);

CREATE TABLE TrainerAvailability (
    trainer_id INT PRIMARY KEY,
    start_time TIME,
    end_time TIME,
    date VARCHAR(20),
    foreign key (trainer_id) references Users(id)
);

CREATE TABLE equipment (
    equipment_id INT PRIMARY KEY,
    name VARCHAR(255),
    check_update DATE,
    bool_is_available BOOLEAN
);

CREATE TABLE Sessions (
    session_id SERIAL PRIMARY KEY,
    trainer_id INT,
    member_id INT,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    date DATE,
    is_group BOOLEAN,
    room_number INT,
    equipment_id INT,
    foreign key (equipment_id) references equipment,
    foreign key (trainer_id) references Users(id),
    foreign key (member_id) references Users(id)
);
