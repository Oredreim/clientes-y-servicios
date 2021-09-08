# Taller Clientes y Servicios
## Descripción
En el **Taller de Clientes y Servicios** se realizarán diferentes retos en los que se explorarán los conceptos de esquemas de nombres y de clientes y servicios. Adicionalmente, el taller también explorará la arquitectura de las aplicaciones distribuidas sobre internet, para así ver el funcionamiento detallado de una aplicación web capaz de recibir múltiples solicitudes no concurrentes, creando un framework manualmente muy similar al funcionamiento de Spark que le permite publicar servicios web ```get``` y ```post``` para poder acceder a recursos estáticos como páginas web, javascripts, imágenes CSSs, entre otras cosas, desplegado usando un servidor web llamado Heroku para poder acceder a ella de manera totalmente remota. Para verificar el funcionamiento de cada uno de los requisitos, se realizó una simulación de la página de la Registraduría Nacional del Estado Civil, en la cual el usuario tiene una interfaz, y al digitar la URL en la que se encuentran las bases de datos, puede observar características como nombres y apellidos de ciudadanos junto con su dirección, cada una de ellas almacenada en una base de datos.
## Prerrequisitos
Para la realización y ejecución tanto del programa como de las pruebas de este, se requieren ser instalados los siguientes programas:
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [Heroku](https://www.heroku.com/). Plataforma en la nube como servicio (PaaS en sus siglas en inglés) basada en contenedores. Los desarrolladores usan Heroku para implementar, administrar y escalar aplicaciones modernas.

Para asegurar que el usuario cumple con todos los prerrequisitos para poder ejecutar el programa, es necesario disponer de un Shell o Símbolo del Sistema para ejecutar los siguientes comandos para comprobar que todos los programas están instalados correctamente, para así compilar y ejecutar tanto las pruebas como el programa correctamente.

```
mvn -version
git --version
java -version
```

## Instalación
Para descargar el proyecto de GitHub, primero debemos clonar este repositorio, ejecutando la siguiente línea de comando en GIT.

```
git@github.com:Oredreim/clientes-y-servicios.git
```

## Ejecución
Para compilar el proyecto utilizando la herramienta Maven, nos dirigimos al directorio donde se encuentra alojado el proyecto, y dentro de este ejecutamos en un Shell o Símbolo del Sistema el siguiente comando:

```
mvn package
```
## Pruebas
Para realizar las pruebas correspondientes del proyecto utilizando la herramienta Maven, nos dirigimos al directorio donde se encuentra alojado el proyecto, y dentro de este ejecutamos en un Shell o Símbolo del Sistema el siguiente comando:

```
mvn test
```

Luego de haber ejecutado el comando ```mvn test``` en un Shell o Símbolo del Sistema, debe aparecer la siguiente imagen, demostrando que las pruebas han sido realizadas exitosamente.

## Desplegar en Heroku
Para desplegar el programa en Heroku, se debe realizar clic en el siguiente botón de Heroku para desplegar automáticamente la aplicación directamente desde Heroku. 

[![Deploy](https://www.herokucdn.com/deploy/button.svg)]
* Para ir a la interfaz principal, poner el recurso ```/index.html``` al final de la URL de Heroku. 
* Para consultar toda la información relacionada con la Registraduría Nacional del Estado Civil, poner el recurso ```/Apps/acercade``` al final de la URL de Heroku.
* Para visualizar el logo de la Registraduría Nacional del Estado Civil, poner el recurso ```/RegistraduriaNacionalDelEstadoCivil.png``` al final de la URL de Heroku.
* Para consultar todas las bases de datos de todos los ciudadanos, poner el recurso ```/Apps/informationDB``` al final de la URL de Heroku.

## Construido con
* [Maven](https://maven.apache.org/). Herramienta que se encarga de estandarizar la estructura física de los proyectos de software, maneja dependencias (librerías) automáticamente desde repositorios y administra el flujo de vida de construcción de un software.
* [GIT](https://git-scm.com/). Sistema de control de versiones que almacena cambios sobre un archivo o un conjunto de archivos, permite recuperar versiones previas de esos archivos y permite otras cosas como el manejo de ramas (branches).
* [JUnit](https://junit.org/junit5/). Framework de Java que permite la realización de la ejecución de clases de manera controlada, para poder comprobar que los métodos realizan su cometido de forma correcta.
* [IntelliJ](https://www.jetbrains.com/idea/). es un entorno de desarrollo integrado (IDE) para el desarrollo de programas informáticos. Es desarrollado por JetBrains (anteriormente conocido como IntelliJ), y está disponible en dos ediciones: edición para la comunidad y edición comercial.
* [Java](https://www.oracle.com/java/). Lenguaje de programación de propósito general, es decir, que sirve para muchas cosas, para web, servidores, aplicaciones móviles, entre otros. Java también es un lenguaje orientado a objetos, y con un fuerte tipado de variables.
* [Heroku](https://www.heroku.com/). Plataforma en la nube como servicio (PaaS en sus siglas en inglés) basada en contenedores. Los desarrolladores usan Heroku para implementar, administrar y escalar aplicaciones modernas.
* [DBeaver](https://dbeaver.io/). Herramienta de gestión de base de datos gráfica, gratuita y de código abierto para que los desarrolladores y administradores de bases de datos creen y gestionen bases de datos en una amplia gama de sistemas de gestión de bases de datos (DBMS).
* [CircleCI](https://circleci.com/). Plataforma moderna de integración continua y entrega continua (CI / CD) que se encarga de automatizar la construcción, pruebas e implementación de software.
* [Obsidian](https://obsidian.md). Obsidian is a powerful knowledge base on top of
a local folder of plain text Markdown files.


## Autor
[Cristian Camilo Piñeros Arevalo](https://github.com/Oredreim)
## Licencia & Derechos de Autor
**©** Cristian Camilo Piñeros Arevalo, Estudiante de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería Julio Garavito

Licencia bajo la [GNU General Public License](https://github.com/Oredreim/clientes-y-servicios/blob/main/LICENSE).
