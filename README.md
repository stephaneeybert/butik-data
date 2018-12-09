# butik-data
A job test assignment project data layer

Only building the project
```
mvn clean install
```

Installing an integration test database

The database schema is automatically created by Flyway when running the integration tests.
But a database still needs to be created first.

To create the database and its connection configuration, execute the statements from the following files...

...if running against MySQL or Mariadb
```
src/main/resources/mysql/mysql-database.sql
src/main/resources/mysql/mysql-user.sql
```
... if running against PostgreSQL
```
src/main/resources/postgresql/pg-database.sql
src/main/resources/postgresql/pg-user.sql
```
...if running against Oracle XE, use the default user and adjust the user details if needed

Building and running some integration tests
```
mvn clean install -Denv="test" -Ddb="h2"
mvn clean install -Denv="test" -Ddb="mysql"
mvn clean install -Denv="test" -Ddb="postgresql"
```

The data layer is compatible with H2, MySQL, Postgresql and Oracle
```
-Denv="prod" (an empty env string is considered as prod)
-Denv="test"
-Ddb="h2"
-Ddb="mysql"
-Ddb="postgresql"
-Ddb="oracle"
```
