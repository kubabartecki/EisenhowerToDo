CREATE SEQUENCE task_id_sequence INCREMENT 50;

CREATE TABLE task
(
    id              INTEGER DEFAULT nextval('task_id_sequence') PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    description     TEXT,
    status          INTEGER      NOT NULL,
    category        INTEGER      NOT NULL,
    created_at_date TIMESTAMP    NOT NULL,
    due_date        TIMESTAMP
);

INSERT INTO task (title, description, status, category, created_at_date, due_date)
VALUES ('Task 1', 'Description for Task 1', 10, 10, '2024-01-01 10:00:00', '2024-01-05 10:00:00'),
       ('Task 2', 'Description for Task 2', 20, 20, '2024-01-02 11:00:00', '2024-01-06 11:00:00'),
       ('Task 3', 'Description for Task 3', 10, 30, '2024-01-03 12:00:00', '2024-01-07 12:00:00'),
       ('Task 4', 'Description for Task 4', 30, 40, '2024-01-04 13:00:00', '2024-01-08 13:00:00')
ON CONFLICT DO NOTHING;
