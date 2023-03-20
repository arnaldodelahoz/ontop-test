# ONTOP test de Spring Boot

## Requisitos previos

-   Java 8 o superior
-   Maven 3.2 o superior

## Cómo correr la aplicación

1.  Clonar este repositorio: `git clone https://github.com/arnaldodelahoz/ontop-test.git`
2.  Navegar hasta el directorio raíz del proyecto: `cd ontop-test`
3.  Configurar una bdd para postgres llamada `ontop-wallet`
3.  Compilar el proyecto: `mvn clean install`
4.  Correr la aplicación: `mvn spring-boot:run`
5.  Abrir un navegador web y acceder a `http://localhost:8080`
6.  Opcional `http://localhost:8080/swagger-ui/index.html`

## Como correr la aplicacion con docker
1.  Clonar este repositorio: `git clone https://github.com/arnaldodelahoz/ontop-test.git`
2.  Navegar hasta el directorio raíz del proyecto: `cd ontop-test`
3.  Cambiar el profile en archivo application.properties set docker
3.  Ejecutar el comando `docker-compose up --build -d` Nota: El flag `--build` solo se ejecuta la primera vez.
## Cómo cambiar la configuración

-   Para cambiar el puerto de la aplicación, modificar la propiedad `server.port` en el archivo `application.properties`
-   Para cambiar la configuración de la base de datos, modificar las propiedades `spring.datasource.url`, `spring.datasource.username` y `spring.datasource.password` en el archivo `application.properties` 
