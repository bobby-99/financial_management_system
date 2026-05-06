CREATE TABLE categories (
    id UUID PRIMARY KEY ,
    name VARCHAR(100) NOT NULL ,
    user_id UUID NOT NULL
);