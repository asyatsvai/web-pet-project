CREATE TABLE common.user
(
    id   UUID,
    -- immutable
    position bigserial NOT NULL,
    name varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    birth_date date NOT NULL,
    -- mutable
    employment_id int4,
    CONSTRAINT user_pk PRIMARY KEY (id),
    CONSTRAINT user_fk_employment FOREIGN KEY (employment_id) REFERENCES common.employment (id)
) WITH ( FILLFACTOR = 95 );

COMMENT ON TABLE common.user
    IS 'Сотрудник';

COMMENT ON COLUMN common.user.id
    IS 'Идентификатор сотрудника';
COMMENT ON COLUMN common.user.position
    IS 'Порядковый номер';
COMMENT ON COLUMN common.user.name
    IS 'Имя сотрудника';
COMMENT ON COLUMN common.user.surname
    IS 'Фамилия сотрудника';
COMMENT ON COLUMN common.user.birth_date
    IS 'Дата рождения сотрудника';

-- DROP TABLE common.user;
