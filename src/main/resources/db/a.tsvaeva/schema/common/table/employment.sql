CREATE TABLE common.employment
(
    id  bigserial NOT NULL,
    -- immutable
    user_id UUID NOT NULL,
    department varchar(16) NOT NULL,
    position varchar(50) NOT NULL,
    -- mutable
    period daterange NOT NULL,
    CONSTRAINT employment_pk PRIMARY KEY (id),
    CONSTRAINT employment_fk_user FOREIGN KEY (user_id) REFERENCES common.user (id)
) WITH ( FILLFACTOR = 95 );

COMMENT ON TABLE common.employment
    IS 'Сотрудник';

COMMENT ON COLUMN common.employment.id
    IS 'Идентификатор сотрудника';
COMMENT ON COLUMN common.employment.department
    IS 'Отдел';
COMMENT ON COLUMN common.employment.position
    IS 'Должность';
COMMENT ON COLUMN common.employment.period
    IS 'Период работы';

-- DROP TABLE common.employment;
