# butik-data
A job test assignment project data layer

To only build the project
mvn clean install

To build and run some integration tests
mvn clean install -Denv="test" -Ddb="h2"
mvn clean install -Denv="test" -Ddb="mysql"
mvn clean install -Denv="test" -Ddb="postgresql"

The data layer is compatible with H2, MySQL, Postgresql and Oracle
-Denv="prod" (an empty env string is considered as prod)
-Denv="test"
-Ddb="h2"
-Ddb="mysql"
-Ddb="postgresql"
-Ddb="oracle"
