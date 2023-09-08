# Справочник сведений ЦБ РФ о кредитно-финансовых учреждениях

## Техническое задание
Разработать web-приложение для администрирования справочной информации о кредитнофинансовых учреждениях.
Справочник должны иметь возможность добавления, редактирования, удаления записей через
пользовательский интерфейс, а также иметь механизм пакетной загрузки и/или обновления
справочных данных на основе предоставляемых сведений ЦБ РФ (Справочник БИК – файл в формате XML).

Для хранения справочной информации о кредитно-финансовых учреждениях необходимо
спроектировать базу данных.

Приложение должно иметь API доступа “внешних” сервисов к справочной информации и её
поиску.

Дополнительные детали:
* Описать API при помощи Swagger;
* Приложение должно иметь настраиваемый логгер;
* Интерфейс должен быть “дружелюбным” (предупреждения, сообщения, подсветки);
* Исключить проблему повторных нажатий удалить/сохранить/обновить в случае “подвисания”
  сети/сервера и других факторов;
* Проблемы в работе приложения (бэка или фронта) не должны ставить пользователя в тупик
  (недопустимо отображать белый экран, ошибки сервера в виде Stacktrace, кодов 400-500 и
  прочее).

## Технологии
* Backend: Spring Boot, Spring MVC, Spring security, Spring Data JPA, Lombok;
* База данных: .h2;
* Frontend: HTML - CSS, Bootstrap, FreeMarker;
* Система сборки: Maven;
* Парсинг XML: JAXB;
* Журналирование: Sfl4j + Logback.

## Запуск
Чтобы собрать и запустить проект, выполните следующие действия:
* Клонируйте репозиторий: `git clone https://github.com/Tiltuem/Handbook-Bank.git`
* Перейдите в каталог проекта: cd handbook-Bank-develop.;
* Сборка проекта: mvn clean install;
* Запустите проект: mvn Spring-boot:run.

-> Приложение будет доступно по адресу http://localhost:8080.
