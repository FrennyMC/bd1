Weather-App
============

[![Weather-App Build Pipeline](https://github.com/FrennyMC/bd1/actions/workflows/weather-report-build.yml/badge.svg?branch=main)](https://github.com/FrennyMC/bd1/actions/workflows/weather-report-build.yml)

Aplicación para obtener y publicar pronosticos del clima.

## Instrucciones de construcción:

'''bash

# compilar, compilar recursos

mvn clean compile 

# ejecutar pruebas unitarias

mvn clean test

# ejecutar pruebas unitarias, crear JAR sin dependencias

mvn clean package 

mvn clean package assembly:single

## Instrucciones de ejecución:

'''bash

# ver opciones disponibles 

java -jar weather-app-1.0.2-SNAPSHOT-jar-with-dependencies.jar

# Obtener un pronóstico del clima de una ciudad 

java -jar weather-app-1.0.2-SNAPSHOT-jar-with-dependencies.jar by-city "Alajuela"
java -jar weather-app-1.0.2-SNAPSHOT-jar-with-dependencies.jar bc "Alajuela"

# Crear un nuevo pronóstico


# Actualizar un pronóstico del clima

# Borrar un pronóstico del clima

java -jar weather-app-1.0.2-SNAPSHOT-jar-with-dependencies.jar df 1

