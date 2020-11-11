CREATE TABLE IF NOT EXISTS LESSON
(
    ID       BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME     VARCHAR(20) NOT NULL,
    DURATION DOUBLE,
    COURSE_ID BIGINT NOT NULL,
    FOREIGN KEY (COURSE_ID) REFERENCES COURSE(ID)

)
