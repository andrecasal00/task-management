CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tbl_category(
    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);

CREATE TABLE tbl_user(
    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    email VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);

CREATE TABLE tbl_task(
    uuid UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(250) NOT NULL,
    description TEXT,
    status VARCHAR(250) NOT NULL,
    dueDate DATE NOT NULL,
    user_uuid UUID,
    category_uuid UUID,
    CONSTRAINT fk_user_uuid FOREIGN KEY (user_uuid) REFERENCES tbl_user(uuid) ON DELETE CASCADE,
    CONSTRAINT fk_category_uuid FOREIGN KEY (category_uuid) REFERENCES tbl_category(uuid) ON DELETE CASCADE
);