### Пример использования JDBC и простых SQL запросов.

#### Установка PostgreSQL

В примере есть [docker-compose.yml][docker compose] файл, который позволяет запустить PostgreSQL с предустановленной базой данных.  
Но для начана понадобится установить Docker и docker-compose:
* [Установка на Windows][docker windows install]
* [Установка на Mac][docker mac install]
* [Установка docker compose][docker compose install]

После чего в файле [docker-compose.yml][docker compose] укажите путь на диске, где будут располагаться файлы БД:  
```yaml
  volumes:
    - /tmp/docker/postgresql:/var/lib/postgresql
```

Когда всё настроено можно запустить БД и подключиться к ней. Для этого достаточно в директории, где находится [docker-compose.yml][docker compose]
выполнить: 
```shell script
docker-compose up
```

Для того, чтобы остановить БД достаточно выполнить:
```shell script
docker-compose down
```

#### Без использования Docker

Использовать Docker совсем не обязательно, можно установить Postgres как есть. В процессе установки не забудьте
задать пароль для суперпользователя postgres. Так же понадобится создать базу `ifmo` и пользователя `ifmo` с паролем
`q1w2e3`. Это нужно для простоты запуска уже готовых примеров.

Для создания БД и пользователя войдите в psql как пользователь postgres и выполните:
```shell script
postgres=# create database ifmo;
postgres=# create user ifmo with encrypted password 'q1w2e3';
postgres=# grant all privileges on database ifmo to ifmo;
```

Те же команды можно выполнить в PgAdmin:
```roomsql
create database ifmo;
create user ifmo with encrypted password 'q1w2e3';
grant all privileges on database ifmo to ifmo;
```

#### Установка PgAdmin

Для того, чтобы была возможность подключиться к БД используя графическую оболочку, стоит [установить PgAdmin][pgadmin install].

Если всё хорошо, то можно подключиться к серверу БД [используя PgAdmin][pgadmin connect], 
укажите хост `localhost:5432`, пользователь `ifmo`, база данных `ifmo` и пароль `q1w2e3`.

#### Создание тестовых таблиц с данными

Теперь, чтобы было с чем работать, в PgAdmin выберите `Tools -> Query Tool`. 
Скопируйте содержимое файла [course.sql][course.sql] в открывшееся окно и выполните.
Этот скрипт создаст все таблицы и тестовые данные.

Чтобы проверить, что все данные есть, выполните, например, следующий запрос:
```roomsql
select * from course;
``` 
Если всё настроено корректно, то вы увидете список курсов.

[docker compose]: https://github.com/XMitya/jdbc-example/blob/master/src/main/docker/docker-compose.yml
[course.sql]: https://github.com/XMitya/jdbc-example/blob/master/bin/course.sql
[docker windows install]: https://docs.docker.com/docker-for-windows/install/
[docker mac install]: https://docs.docker.com/docker-for-mac/install/
[docker compose install]: https://docs.docker.com/compose/install/ 
[pgadmin install]: https://www.pgadmin.org/download/
[pgadmin connect]: https://www.pgadmin.org/docs/pgadmin4/latest/connecting.html
[postgres install]: https://www.postgresql.org/download/
[create db]: https://medium.com/coding-blocks/creating-user-database-and-adding-access-on-postgresql-8bfcd2f4a91e