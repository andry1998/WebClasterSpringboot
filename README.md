# WebClusterSpringboot
## Структура базы данных

В спроектированной базе данных хранится две таблицы: таблица пользователей системы и таблица студентов на кластере.

![image](https://user-images.githubusercontent.com/47540160/186232807-e1e5c81b-f33e-4d36-8442-3839f903ebd7.png)

![image](https://user-images.githubusercontent.com/47540160/186233091-a98729a0-093b-4d88-933d-4bb768dd13eb.png)

## Пользовательский интерфейс
 
Пользовательский проект разработан с помощью фреймворка Angular. Бэкенд работает на сервере Apache Tomcat локальном хосте http://localhosh:8080. В данном проекте информация о пользователях системы, а также о студентах,	добавленных	на	кластер	хранится	на	локальной	базе	данных
PostgreSQL.
Разработанный проект Spring Boot, обращаясь к базе данных, выводит список всех пользователей и студентов при переходе на ссылки http://localhosh:8080/persons и http://localhosh:8080/students соответственно.

![image](https://user-images.githubusercontent.com/47540160/186233428-7fa835fd-650c-4077-be44-c41317bf505f.png)

![image](https://user-images.githubusercontent.com/47540160/186233461-4d196888-9504-4461-9ec7-8bc36112c48f.png)

При переходе на данный хост, Spring делает проверку на права доступа пользователя. Если пользователь зарегистрирован в системе, в ответ он получает JWS токен, который является уникальным, идентифицирующем пользователя ключом.

![image](https://user-images.githubusercontent.com/47540160/186233575-a2da0c14-1b72-4ed8-8a74-35ebfd38d34c.png)

Данный токен пользователь отправляет в запросе на авторизацию в систему, после чего при успешном вводе может зайти в систему под своим именем.

![image](https://user-images.githubusercontent.com/47540160/186233660-6ad7f248-85df-48bd-8e19-689018c9f2e2.png)

Далее, если пользователь залогинился в системе, то он в зависимости от прав доступа получает определенные права к просмотру и редактированию данных.

![image](https://user-images.githubusercontent.com/47540160/186233746-e8641ad3-d8f4-4881-8e76-6476e07e28d9.png)

Далее	начинает	работать	Angular.	Переходим	на	локальный	хост http://localhosh:4200/auth/login, попадаем на страницу авторизации,
 проходим авторизацию, попадаем на сервер приложения.
 
 ![image](https://user-images.githubusercontent.com/47540160/186233944-cc94c744-8c15-42e3-b131-99836a3eeb28.png)


![image](https://user-images.githubusercontent.com/47540160/186233969-cc6e27c3-0434-4697-8116-cba68faf5670.png)

Юзеры могут авторизоваться либо как пользователь, либо как администратор. В зависимости от роли, у пользователей будут ограничения на некоторые действия на сервере. На странице возможны следующие действия:
-	просмотр списка студентов, разрешено и для всех пользователей 

![image](https://user-images.githubusercontent.com/47540160/186234083-f63160e6-f3fc-46e2-b0a9-27b2dc97ae8d.png)

-	просмотр списка пользователей, разрешено для всех пользователей 

![image](https://user-images.githubusercontent.com/47540160/186234126-59926666-2c82-4c65-bf76-1450de462aef.png)


-	добавление новых студентов, разрешено для всех пользователей

![image](https://user-images.githubusercontent.com/47540160/186234214-04fcf00a-9853-4182-a966-8080bd5c517b.png)

-	добавление новый пользователей, разрешено только для администратора

![image](https://user-images.githubusercontent.com/47540160/186234259-b3bd8f2f-420b-4038-8406-a333f3d5ff4e.png)

-	редактирование	данных	о	студентах,	разрешено	только	для администратора 

![image](https://user-images.githubusercontent.com/47540160/186234305-c5223222-fb3f-43c8-877a-0c8c47d0d9c4.png)

-	редактирование	данных	о	пользователях,	разрешено	только	для администратора 

![image](https://user-images.githubusercontent.com/47540160/186234377-55b5379f-3048-4d2b-bc4b-03ec2b71e280.png)

-	удаление студентов или пользователей из системы, разрешено только администратору 

![image](https://user-images.githubusercontent.com/47540160/186234439-717de612-ed85-4b7d-aea7-30b4f8041576.png)

При создании нового студента в поле для ввода пароля будет работать скрипт генерирования случайных паролей 

![image](https://user-images.githubusercontent.com/47540160/186234504-ad25e5fd-2830-46bb-a645-80ba804e6411.png)

При создании, а также редактировании данных о студенте, вся информация записывается и обновляется в файле, в котором можно будет просмотреть список всех студентов, которые хранятся в настоящее время в базе данных. Файл располагается в корне проекта.

![image](https://user-images.githubusercontent.com/47540160/186234632-8d4f6b89-389d-48d4-9592-6d90eadcb632.png)

Разработанная web-система управления пользователями кластера позволяет упростить систему управления, добавления, изменения и удаления данных о студентах ИАТЭ НИЯУ МИФИ.
