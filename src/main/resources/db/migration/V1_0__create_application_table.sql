create table if not exists APPLICATION (
    ID bigint AUTO_INCREMENT NOT NULL PRIMARY KEY,
    STATUS varchar(50),
    COURSE_IDEA varchar(50),
    USER_ID bigint not null,
    FOREIGN KEY (USER_ID)
        REFERENCES USER(ID)
)