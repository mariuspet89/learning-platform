CREATE TABLE IF NOT EXISTS LESSON_CONTENT
(
    ID      BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    CONTENT VARCHAR(50),
    LESSON_ID BIGINT,
    FOREIGN KEY (LESSON_ID) REFERENCES LESSON(ID)
);
