CREATE TABLE IF NOT EXISTS QUIZ_ITEM
(
    ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    QUIZ_ID BIGINT NOT NULL,
    QUESTION VARCHAR(255) NOT NULL,
    QUIZ_ITEM_TYPE_ID BIGINT NOT NULL,
    FOREIGN KEY(QUIZ_ID) REFERENCES QUIZ(ID),
    FOREIGN KEY(QUIZ_ITEM_TYPE_ID) REFERENCES QUIZ_ITEM_TYPE(ID)
);