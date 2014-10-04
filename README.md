commons-do [![Build Status](https://travis-ci.org/developersdo/commons-do.svg?branch=master)](https://travis-ci.org/developersdo/commons-do)
==========
Inspirado en data.developers.do está a disposición de los Developers Dominicanos el proyecto commons-do. Tiene como enfoque principal suplir de los algoritmos y funcionalidades más comunes y usadas con frecuencia.

El proyecto inicialmente contempla todo el API hasta el momento disponible por http://data.developers.do y puesto como librería de dependencias desde Maven para desarrolladores Java. Para colaborar pueden hacer fork al proyecto en https://github.com/developersdo/commons-do.

####Instalación:
En el POM.xml de su proyecto debe agregar:

######Repositorio
```XML
<repository>
    <id>devdom</id>
    <name>Developers Dominicanos Repository</name>
    <url>http://50.19.213.136:8090/repository/</url>
</repository>
```
######Dependencia
```XML
<dependency>
    <groupId>org.devdom</groupId>
    <artifactId>commons-do</artifactId>
    <version>0.8.1</version>
    <type>jar</type>
</dependency>
```