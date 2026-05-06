CREATE TABLE transactions (

    id UUID PRIMARY KEY,
    amound DOUBLE PRECISION NOT NULL ,
    description TEXT NOT NULL,
    user_id UUID NOT NULL ,
    created_at TIMESTAMPTZ NOT NULL

);