# Bell Integrator пример для учебного задания java
# Репозиторий
git - https://github.com/azEsm/empty_project

# Для запуска необходимо установить следующее ПО
- JDK 8
- Maven
# Установка JDK
https://www.oracle.com/ru/java/technologies/javase-downloads.html
# Установка Maven
- [Install Maven on Windows](https://www.baeldung.com/install-maven-on-windows-linux-mac#installing-maven-on-windows)
- [Install Maven on Linux](https://www.baeldung.com/install-maven-on-windows-linux-mac#installing-maven-on-linux)
- [Install Maven on Mac OSX](https://www.baeldung.com/install-maven-on-windows-linux-mac#installing-maven-on-mac-os-x)

# Запуск проекта
1. В терминале открыть корневую директорию проекта (содержит файл pom.xml)
2. Выполнить команду `mvn spring-boot:run`

# Список REST-запросов

## Справочники
- api/docs – получить список всех доступных типов документов
- api/countries – получить список всех доступных стран

## Организации
- api/organization/list – получить список организаций
- api/organization/{id} - получить подробную информацию об организации
- api/organization/update - обновить данные существующей организации
- api/organization/save - сохранить новую организацию

## Офисы
- api/office/list – получить список офисов
- api/office/{id} - получить подробную информацию об офисе
- api/office/update - обновить данные существующего офиса
- api/office/save - сохранить новый офис
   
## Пользователи
- api/user/list – получить список пользователей
- api/user/{id} - получить подробную информацию о пользователе
- api/user/update - обновить данные существующего пользователя
- api/user/save - сохранить нового пользователя

