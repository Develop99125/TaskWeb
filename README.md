# База данных (PostgresSQL)
Имя базы данных - "photoWeb".

Дамп базы данных - файл "photoWeb_dump.sql".

После импорта базы данных необходимо обновить "последовательность" в таблице "images", выполнив следующий SQL-запрос:

```SQL
SELECT setval('images_id_seq', (SELECT MAX(id) FROM images));
```
______
# Регистрация. Зарегистрированные пользователи
После регистрации новому аккаунту присваивается роль "Пользователь". Установка других ролей выполняется через базу данных.

Аккаунт с ролью "_Администратор_":

+ Логин: __admin__

+ Пароль: __admin__


Аккаунт с ролью "_Пользователь_":

+ Логин: __nikk__

+ Пароль: __nikk__
______
# Начальный набор изображений
В базе данных уже сформирован начальный набор изображений. 

"Сломанные" изображения созданы специально и используются в качестве заглушек для демонстрации механизма навигации между страницами (пачками) изображений. 
