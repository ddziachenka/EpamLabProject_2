
Training project "Theater Tickets Sale"

Stack: Spring (Core, MVC, Security, JDBC Template), H2, Maven, Log4j



Примечания:

1) Во время разработки использовался Tomcat 8.0.32. На других версиях работоспособность приложения не гарантируется.

2) Через Spring Security реализовано 2 пользователя:

1. Логин - user, пароль - 12345, роль - USER

2. Логин - admin, пароль - 12345, роль - ADMIN

У пользователя с ролью ADMIN на главной странице добавляется ссылка “Admin panel”, где он может удалять события.

3) Файл с базой данных H2 лежит в папке dbH2, для ее работоспособности необходимо в resources/persistence.properties поправить url