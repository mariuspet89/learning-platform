CREATE TABLE IF NOT EXISTS job_titles
(

    id int NOT NULL auto_increment PRIMARY KEY ,
    job_title ENUM ('Java Developer', '.NET DEVELOPER',
        'DevOps Engineer', 'Business Analyst',
        'UI/UX Designer', 'Test Engineer','Project Delivery Manager') NOT NULL


);