-- Вставка типов документов
INSERT INTO Document_type (code, name)
VALUES (3, 'Свидетельство о рождении');
INSERT INTO Document_type (code, name)
VALUES (7, 'Военный билет ');
INSERT INTO Document_type (code, name)
VALUES (8, 'Временное удостоверение, выданное взамен военного билета  ');
INSERT INTO Document_type (code, name)
VALUES (10, 'Паспорт иностранного гражданина');
INSERT INTO Document_type (code, name)
VALUES (11,
        'Свидетельство о рассмотрении ходатайства о признании ' ||
        'лица беженцем на территории Российской Федерации по существу');
INSERT INTO Document_type (code, name)
VALUES (12, 'Вид на жительство в Российской Федерации');
INSERT INTO Document_type (code, name)
VALUES (13, 'Удостоверение беженца');
INSERT INTO Document_type (code, name)
VALUES (15, 'Разрешение на временное проживание в Российской Федерации');
INSERT INTO Document_type (code, name)
VALUES (18, 'Свидетельство о предоставлении временного убежища на территории Российской Федерации');
INSERT INTO Document_type (code, name)
VALUES (21, 'Паспорт гражданина Российской Федерации');
INSERT INTO Document_type (code, name)
VALUES (23, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства');
INSERT INTO Document_type (code, name)
VALUES (24, 'Удостоверение личности военнослужащего Российской Федерации');
INSERT INTO Document_type (code, name)
VALUES (91, 'Иные документы');

--Вставка стран
INSERT INTO Country (code, name)
VALUES (634, 'Российская Федерация');

--Вставка организаций
INSERT
INTO Organization (name, full_name, inn, kpp, address, phone, is_active)
VALUES ('СБ', 'СберБанк', '123456789012', '123456789', 'ул. Тверская, 1', '8005553535', true);
INSERT INTO Organization (name, full_name, inn, kpp, address, phone, is_active)
VALUES ('BI', 'Bell Integrator', '210987654321', '987654321', 'ул. Пушкинская, 2', '4999996565', true);

--Вставка офисов
INSERT INTO Office (name, phone, address, is_active, org_id)
VALUES ('Рассвет', '4950765698', 'п. Столярный, 3', true, 2);
INSERT INTO Office (name, phone, address, is_active, org_id)
VALUES ('Восход', '4950735692', 'п. Столярный, 10', true, 2);
INSERT INTO Office (name, phone, address, is_active, org_id)
VALUES ('Мир', '4990835628', 'б. Новинский, 32', true, 1);

--Вставка документов
INSERT INTO Document (doc_type_id, doc_number, doc_date)
VALUES (10, '555555555555', CURRENT_DATE());
INSERT INTO Document (doc_type_id, doc_number, doc_date)
VALUES (10, '444444444444', CURRENT_DATE());

--Вставка пользователей
INSERT INTO User (office_id, first_name, second_name, middle_name,
                  position, doc_id, phone, citizenship_code, is_identified)
VALUES (1, 'Геннадий', 'Геннадиевич', 'Горин', 'Менеджер', 1, '9995553535', 1, true);
INSERT INTO User (office_id, first_name, second_name, middle_name,
                  position, doc_id, citizenship_code, is_identified)
VALUES (1, 'Цветогор', 'Грайрович', 'Петрухин', 'Аналитик', 2, 1, true);

INSERT INTO User (office_id, first_name, position)
VALUES (3, 'Лесоруб', 'Разработчик');