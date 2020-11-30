# Proyecto Dos Redes, Servidor HTTP & HTTPS
 
La intención de este proyecto es emular la funcionalidad de un servidor web, la cual consiste en almacenar diferentes páginas web y al consultar su direccion en un navegador se nos muestra en pantalla.

Para poder ejecutar el código de éste repositorio se necesita de un Entorno de Desarrollo Integrado (IDE) llamado NetBeans, pero además de ello y puesto que la aplicación está escrita en código Java son necesarios dos programas adicionales, que son los siguientes, Java Runtime Environment (JRE) que se puede descargar en https://www.java.com/es/download/ y, el Java Development Kit (JDK) pero para el mismo existe una opción que viene en conjunto con NetBeans, que se puede obtener en https://www.oracle.com/technetwork/es/java/javase/downloads/jdk-netbeans-jsp-3413139-esa.html

## Pasos para instalar el software necesario junto con la obtención del código del proyecto.

### 1. Ir al la página de descarga de Oracle, donde se descarga en conjunto NetBeans y JDK

![](https://github.com/JassonRomero/ProyectoRedes/blob/master/descargarNetbeans.png)

Para proceder con la descarga se tiene que seleccionar la opción que dice "Accept License Agreement",
y luego, estrictamente abajo estan listadas las opciones de descarga para cada una de las 
distintas arquitecturas.

### 2. Pantalla de inicio de NetBeans

![](https://github.com/JassonRomero/ProyectoRedes/blob/master/initNetBeans.png)

Pantalla de inicio del entorno de desarrollo integrado NetBeans, en el cual se pueden ejecutar,
y escribir aplicaciones en código Java, además de otros lenguajes de programación

### 3. Descarga del código del repositorio de Github

![](https://github.com/JassonRomero/ProyectoDosRedesLuisStevenJasson/blob/img/imagen.png)

En la barra superior de la pantala principal del IDE, hay una opción llamada "team", la cual
clickeamos para inmediatamente hacer lo mismo con la opción git, del menú que desplega, a 
su vez ésta última también desplega un menú, del que nos intereca el ítem "clone". Que nos
muestra una ventana nueva en la que se puede descargar el código contenido en el repositorio
remoto; la misma es mostrada a continuación.

![](https://github.com/JassonRomero/ProyectoRedes/blob/master/configurarRepositorio.png)

Ya en ésta parte podemos ingresar la url del repositorio, junto con los datos de nuestro
usuario en la plataforma.

# Descripción Técnica
## Servidor Web HTTP
![](https://github.com/JassonRomero/ProyectoDosRedesLuisStevenJasson/blob/img/ServidorWeb.PNG)
### static final File RutaWeb = new File("ArchivosServidorWeb\\");
Aqui se define la ruta por defecto donde se buscan todas las páginas web por lo cual si se desea acceder a las páginas de otro lado se debe modificar esta dirección o agregar las páginas en este lugar.
### static final String PaginaPorDefecto = "index.html";
Cuando no se especifica ninguna página en concreto se mostrara la que se coloque en esta sección.
### static final String ArchivoNoEncontrado = "Error.html";
Aca se coloca eol nombre de la página que se encarga de mostrar cuando ocurre un error por si se quiere cambiar seria solo sustituir por la nueva página de error.
### final int PuertoConeccion = 9009;
Aca se especifica por el puerto por el cual se estara conectado por lo cual si se desea conectar por otro puerto seria solo modificar este valor.
## Servidor Web HTTPS
