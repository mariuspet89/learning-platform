CREATE TABLE IF NOT EXISTS USER_TYPES (

    ID int NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    USER_TYPE ENUM ('Admin', 'Trainer', 'Trainee') NOT NULL

);

INSERT into USER_TYPES values(null, 'Admin');
INSERT into USER_TYPES values(null, 'Trainer');
INSERT into USER_TYPES values(null, 'Trainee');