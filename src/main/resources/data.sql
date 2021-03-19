-- Вставка типов документов
INSERT INTO Document (id, version, code, name)
VALUES (1, 0, 3, 'Свидетельство о рождении');
INSERT INTO Document (id, version, code, name)
VALUES (2, 0, 7, 'Военный билет ');
INSERT INTO Document (id, version, code, name)
VALUES (3, 0, 8, 'Временное удостоверение, выданное взамен военного билета  ');
INSERT INTO Document (id, version, code, name)
VALUES (4, 0, 10, 'Паспорт иностранного гражданина');
INSERT INTO Document (id, version, code, name)
VALUES (5, 0, 11,
        'Свидетельство о рассмотрении ходатайства о признании ' ||
        'лица беженцем на территории Российской Федерации по существу');
INSERT INTO Document (id, version, code, name)
VALUES (6, 0, 12, 'Вид на жительство в Российской Федерации');
INSERT INTO Document (id, version, code, name)
VALUES (7, 0, 13, 'Удостоверение беженца');
INSERT INTO Document (id, version, code, name)
VALUES (8, 0, 15, 'Разрешение на временное проживание в Российской Федерации');
INSERT INTO Document (id, version, code, name)
VALUES (9, 0, 18, 'Свидетельство о предоставлении временного убежища на территории Российской Федерации');
INSERT INTO Document (id, version, code, name)
VALUES (10, 0, 21, 'Паспорт гражданина Российской Федерации');
INSERT INTO Document (id, version, code, name)
VALUES (11, 0, 23, 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства');
INSERT INTO Document (id, version, code, name)
VALUES (12, 0, 24, 'Удостоверение личности военнослужащего Российской Федерации');
INSERT INTO Document (id, version, code, name)
VALUES (13, 0, 91, 'Иные документы');

--Вставка стран
INSERT INTO Country (id, version, code, name)
VALUES (1, 0, 634, 'Российская Федерация');

--Вставка организаций
INSERT
INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (1, 0, 'СБ', 'СберБанк', '123456789012', '123456789', 'ул. Тверская, 1', '8005553535', true);
INSERT INTO Organization (id, version, name, full_name, inn, kpp, address, phone, is_active)
VALUES (2, 0, 'BI', 'Bell Integrator', '210987654321', '987654321', 'ул. Пушкинская, 2', '4999996565', true);

--Вставка офисов
INSERT INTO Office (id, version, name, phone, address, is_active, org_id)
VALUES (1, 0, 'Рассвет', '4950765698', 'п. Столярный, 3', true, 2);
INSERT INTO Office (id, version, name, phone, address, is_active, org_id)
VALUES (2, 0, 'Восход', '4950735692', 'п. Столярный, 10', true, 2);
INSERT INTO Office (id, version, name, phone, address, is_active, org_id)
VALUES (3, 0, 'Мир', '4990835628', 'б. Новинский, 32', true, 1);

--Вставка пользователей
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position, phone,
                  doc_code, doc_number, doc_date, citizenship_code, is_identified)
VALUES (1, 0, 1, 'Геннадий', 'Геннадиевич', 'Горин', 'Менеджер', '9995553535',
        21, '555555555555', CURRENT_DATE(), 634, true);
INSERT INTO User (id, version, office_id, first_name, second_name, middle_name, position,
                  doc_code, doc_number, doc_date, citizenship_code, is_identified)
VALUES (2, 0, 1, 'Цветогор', 'Грайрович', 'Петрухин', 'Аналитик',
        21, '555555555555', CURRENT_DATE(), 634, true);

INSERT INTO User (office_id, first_name, position)
VALUES (3, 'Лесоруб', 'Разработчик');