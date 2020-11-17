CREATE TABLE IF NOT EXISTS PROGRAM
(
    ID                  BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME                VARCHAR(50) NOT NULL,
    DESCRIPTION         VARCHAR(200),
    START_DATE          DATE,
    END_DATE            DATE,
    COMPETENCE_AREA_ID  BIGINT,
    FOREIGN KEY (COMPETENCE_AREA_ID) REFERENCES COMPETENCE_AREA(ID)
);
