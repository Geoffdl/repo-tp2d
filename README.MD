### application.properties

```
spring.application.name = repo-tp2d
spring.datasource.url = jdbc:h2:mem:test
spring.datasource.driver-class-name = org.h2.Driver
spring.datasource.username = sa
spring.datasource.password =
spring.jpa.database-platform = org.hibernate.dialect.H2Dialect
spring.h2.console.enabled = true
spring.h2.console.path = /h2-console
spring.jpa.hibernate.ddl-auto = update
spring.jpa.defer-datasource-initialization = true

jwt.expires_in = 7200
jwt.secret = maSuperCleSecrete123maSuperCleSecrete123

jwt.cookie = AUTH-TOKEN
```
