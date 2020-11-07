CREATE TABLE IF NOT EXISTS USERS (
 
    ID int AUTO_INCREMENT PRIMARY KEY,
    FIRST_NAME varchar(50),
    LAST_NAME varchar(50),
    EMAIL varchar(50),
    IMAGE_URL varchar(50),
    PASSWORD varchar(50)
)