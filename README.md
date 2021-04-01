# fuzzy-train

a RESTful Messaging API in Spring Boot Gradle project !!

In this project, I have tried to build a simple Messaging application between two end-users like Bank and Customer.

This project Gradle dependency manager with MySQL database and more it uses Spring Data JPA, Lombok for decrease
boilerplate codes, DevTools, Actuators and more.

**Key Feature:**

- One main key feature of this project is: Multipart Files can be sent in message.
- For this: I have used two libraries **apache-commons** and **commons.io**
- Dependencies: look in build.gradle file

`implementation group: 'org.apache.commons', name: 'commons-lang3', version: '3.0'`

`implementation group: 'commons-io', name: 'commons-io', version: '2.8.0'`

- so the files are saved in the file system of your PC or server.
- as I specified inside **FileHandler.java** file:

```java
import org.apache.commons.lang3.SystemUtils;
public class FileHandler {
    String filePath;
    if(SystemUtils.IS_OS_LINUX) {
        filePath = "/opt/mbank/chatimage";
    } else {
        filePath = "D:/mbank/chatimage";
    }
}
```

- So, If your OS is Linux then the files would be saved in '/opt/mbank/chatimage' folder and if your OS is other than
  Linux then the files would be saved inside 'D:/mbank/chatimage' folder. You can also change the containing folder as
  you want.

- and, I have also added Pagination and Sorting feature in this project. You can see it in code.

## Explore Rest APIs

(update: Now I have just added Swagger2 API documentation, so you can just run the application and explore endpoints):
</br>url: `http://127.0.0.1:8080/swagger-ui.html` .

The app defines following CRUD APIs.

    POST /chatServer/user/message/save
    
    GET /chatServer/user/message/get/{userId}
    
    GET /chatServer/admin/conversations/all

    POST /chatServer/admin/messages/message/save

Test these requests using Postman or any other HTTP REST-client.

## Set-Up

**1. Clone project:**

First, locate to your local Directory then hit:

```bash
git clone https://github.com/dbijaya/fuzzy-train.git
```

**2. Database:**

Since I have added the property `spring.jpa.hibernate.ddl-auto=update` in application.properties file, it will
automatically generate tables in database. but before you have to create a database schema.

1. First to enter MySQL server : (enter valid username and password).

```bash
mysql -u <your-username> -p<your-password>
```

1. then write:

```sql
create
database chatapp;
```

**3. Update application.properties:**

+ change

```java
spring.datasource.username=<db-username>
spring.datasource.password=<db-password>
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/chatapp
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

**4. Run API:**

1. ```bash
    $ gradle clean
    ```

1. ```bash
    $ gradle build
    ```

1. ```bash
    $ gradle run
    ```

1. ```bash
    $ gradle check
    ```

**with luck, it should work :)**
Updates are comming...
