CREATE TABLE IF NOT EXISTS Document_type
(
    id      INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL DEFAULT 0 COMMENT 'Служебное поле hibernate',
    code    INTEGER NOT NULL UNIQUE COMMENT 'Код',
    name    VARCHAR(150) COMMENT 'Наименование'
);
COMMENT ON TABLE Document_type IS 'Тип документа';

CREATE TABLE IF NOT EXISTS Document
(
    id         INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER NOT NULL DEFAULT 0 COMMENT 'Служебное поле hibernate',
    doc_type_id  INTEGER COMMENT 'Тип',
    CONSTRAINT fk_user_doc_type FOREIGN KEY (doc_type_id)
        REFERENCES Document_type (id),
    number VARCHAR(25) COMMENT 'Номер',
    date   DATE COMMENT 'Дата выдачи'
);
COMMENT ON TABLE Document IS 'Документ';

CREATE TABLE IF NOT EXISTS Country
(
    id      INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version INTEGER NOT NULL DEFAULT 0 COMMENT 'Служебное поле hibernate',
    citizenship_code    INTEGER NOT NULL UNIQUE COMMENT 'Код',
    name    VARCHAR(25) COMMENT 'Наименование'
);
COMMENT ON TABLE Country IS 'Страна';

CREATE TABLE IF NOT EXISTS Organization
(
    id        INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER      NOT NULL DEFAULT 0 NOT NULL COMMENT 'Служебное поле hibernate',
    name      VARCHAR(25)  NOT NULL COMMENT 'Сокращённое наименование',
    full_name VARCHAR(250) NOT NULL COMMENT 'Полное наименование',
    inn       CHAR(12)     NOT NULL COMMENT 'ИНН',
    kpp       CHAR(9)      NOT NULL COMMENT 'КПП',
    address   VARCHAR(50)  NOT NULL COMMENT 'Адрес',
    phone     CHAR(10) COMMENT 'Номер телефона',
    is_active BOOLEAN               DEFAULT TRUE COMMENT 'Статус'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office
(
    id        INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER NOT NULL DEFAULT 0 COMMENT 'Служебное поле hibernate',
    name      VARCHAR(25) COMMENT 'Наименование',
    phone     CHAR(10) COMMENT 'Номер телефона',
    address   VARCHAR(50) COMMENT 'Адрес',
    is_active BOOLEAN          DEFAULT TRUE COMMENT 'Статус',
    org_id    INTEGER NOT NULL COMMENT 'Идентификатор организации',
    CONSTRAINT fk_organization_office FOREIGN KEY (org_id)
        REFERENCES Organization (id)
);
COMMENT ON TABLE Office IS 'Офис';

CREATE TABLE IF NOT EXISTS User
(
    id               INTEGER     NOT NULL COMMENT 'Уникальный идентификатор пользователя' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER     NOT NULL DEFAULT 0 COMMENT 'Служебное поле hibernate',
    office_id        INTEGER     NOT NULL COMMENT 'Офис',
    CONSTRAINT fk_person_office FOREIGN KEY (office_id)
        REFERENCES Office (id),
    first_name       VARCHAR(25) NOT NULL COMMENT 'Имя',
    second_name      VARCHAR(25) COMMENT 'Фамилия',
    middle_name      VARCHAR(25) COMMENT 'Отчество',
    position         VARCHAR(25) NOT NULL COMMENT 'Должнсть',
    phone            CHAR(10) COMMENT 'Номер телефона',
    doc_id           INTEGER COMMENT 'Документ',
    CONSTRAINT fk_user_doc FOREIGN KEY (doc_id)
        REFERENCES Document (id),
    country_id INTEGER COMMENT 'Код страны',
    CONSTRAINT fk_user_citizenship_code FOREIGN KEY (country_id)
        REFERENCES Country (id),
    is_identified    BOOLEAN              DEFAULT TRUE COMMENT 'Статус'
);
COMMENT ON TABLE User IS 'Пользователь';