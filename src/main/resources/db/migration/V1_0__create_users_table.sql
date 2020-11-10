create table if not exists USERS (

    ID bigint AUTO_INCREMENT NOT NULL PRIMARY KEY,
    FIRST_NAME varchar(50),
    LAST_NAME varchar(50),
    EMAIL varchar(50) UNIQUE NOT NULL,
    IMAGE_URL varchar(50),
    PASSWORD varchar(50),
    JOB_TITLE_ID int,
    COMPETENCE_AREA_ID int,
    USER_TYPE_ID int
);

ALTER TABLE USERS
    ADD FOREIGN KEY (JOB_TITLE_ID) REFERENCES JOB_TITLES(ID),
    ADD FOREIGN KEY (COMPETENCE_AREA_ID) REFERENCES COMPETENCE_AREAS(ID),
    ADD FOREIGN KEY (USER_TYPE_ID) REFERENCES USER_TYPES(ID);