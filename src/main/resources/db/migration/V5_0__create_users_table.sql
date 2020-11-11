create table if not exists USER
(
    ID                 bigint AUTO_INCREMENT NOT NULL PRIMARY KEY,
    FIRST_NAME         varchar(50)           NOT NULL,
    LAST_NAME          varchar(50)           NOT NULL,
    EMAIL              varchar(50) UNIQUE    NOT NULL,
    IMAGE_URL          varchar(50),
    PASSWORD           varchar(50)           NOT NULL,
    JOB_TITLE_ID       bigint                NOT NULL,
    COMPETENCE_AREA_ID bigint                NOT NULL,
    USER_TYPE_ID       bigint                NOT NULL,

    FOREIGN KEY (JOB_TITLE_ID) REFERENCES JOB_TITLE (ID),
    FOREIGN KEY (COMPETENCE_AREA_ID) REFERENCES COMPETENCE_AREA (ID),
    FOREIGN KEY (USER_TYPE_ID) REFERENCES USER_TYPE (ID)
);