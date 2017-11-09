# Conversor de millas a kilómetros - Android en Kotlin con Binding

Es la primera aplicación básica, nos sirve como ejemplo introductorio.

## Enunciado

El [enunciado](http://algo3.uqbar-project.org/material/ejemplos/dominios/conversor) plantea algunas variantes.

## El proyecto
Este proyecto está generado para

* Android Studio 3.0.0 (Septiembre 2017)
* con Gradle 3.0.0 (el que viene con Android Studio)
* para una SDK 26 (Oreo)
* en Kotlin (hay que descargarlo siguiendo [estos pasos](https://kotlinlang.org/docs/tutorials/kotlin-android.html))
* aprovechamos las [Kotlin Android Extensions](https://antonioleiva.com/kotlin-android-extensions/)
* y tiene como agregado el DataBinding (configurado en el build.gradle de la app)

## La arquitectura MVC

* **La vista**: está definida en un .xml
* **El controller**: es el ConversorActivity
* **El modelo de la vista**: es un Conversor que maneja el binding con los controles millas (EditText) y kilómetros (TextView), ambos Strings
* **El modelo de dominio**: es un Conversor que sabe pasar de millas a kilómetros


## Testing

* en la carpeta test encontrarás [un archivo de testeo unitario del conversor](app/src/test/java/ar/edu/uqbar/conversorappkot/ConversorUnitTest.kt)
* y un [test de integración básico](app/src/androidTest/java/ar/edu/uqbar/conversorappkot/ExampleInstrumentedTest.java)

