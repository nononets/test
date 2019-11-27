CREATE TABLE register(
    no NUMBER(10, 0)
        CONSTRAINT no_pk PRIMARY KEY,
    id VARCHAR2(30 BYTE)
        CONSTRAINT id_uq UNIQUE
        CONSTRAINT id_nn NOT NULL,
    password varchar2(30 BYTE)
        CONSTRAINT pw_nn NOT NULL,
    name varchar2(30 BYTE)
        CONSTRAINT name_nn NOT NULL,
    nickname varchar2(10 CHAR)
        DEFAULT 'Users'
        CONSTRAINT nickname_nn NOT NULL,
    email_address varchar2(50 BYTE)
        CONSTRAINT email_address_uq UNIQUE
        CONSTRAINT email_address_nn NOT NULL,
    birthday DATE
        CONSTRAINT birthday_nn NOT NULL,
    gender VARCHAR2(2 CHAR)
        CONSTRAINT gender_nn NOT NULL,
    tel VARCHAR2(13 BYTE)
        CONSTRAINT tel_nn NOT null,
    leave VARCHAR2(1 BYTE)
        DEFAULT 'N'
        CONSTRAINT lave_nn NOT NULL
        CONSTRAINT leave_check_y_n CHECK (leave IN ('Y', 'N')),
    reg_date TIMESTAMP
        DEFAULT SYSTIMESTAMP
        CONSTRAINT reg_date_nn NOT NULL
);

DROP TABLE register;

INSERT INTO register(no, id, password, name, nickname, email_address, birthday, gender, tel)
VALUES(1, 'admin', '1234', 'Admin', '관리자', 'admin@gmail.com', '2019-11-27', 'M', '010-1234-5678');
