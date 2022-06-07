CREATE TABLE common.user
(
    id   UUID,
    -- immutable
    name varchar(50) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
) WITH ( FILLFACTOR = 95 );

COMMENT ON TABLE common.user
    IS 'Клиент';

COMMENT ON COLUMN common.user.id
    IS 'Идентификатор клиента';
COMMENT ON COLUMN common.user.name
    IS 'Имя клиента';

-- DROP TABLE common.user;
