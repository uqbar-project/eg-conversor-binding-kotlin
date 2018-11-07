# Conversor de millas a kilómetros - Android en Kotlin con Binding

Es la primera aplicación básica, nos sirve como ejemplo introductorio.

## Enunciado

El [enunciado](http://algo3.uqbar-project.org/material/ejemplos/dominios/conversor) plantea algunas variantes.

## El proyecto
Este proyecto está generado para

* Android Studio 3.2.1 (Noviembre 2018)
* con Gradle 3.2.1 (el que viene con Android Studio)
* para una SDK 28 (Pie)
* en Kotlin (hay que descargarlo siguiendo [estos pasos](https://kotlinlang.org/docs/tutorials/kotlin-android.html))
* aprovechamos las [Kotlin Android Extensions](https://antonioleiva.com/kotlin-android-extensions/)
* y tiene como agregado el DataBinding (que antes se configuraba como dependencia `kapt` en el build.gradle de la app y [a partir de las versiones nuevas de Android Studio ya no es necesario](https://stackoverflow.com/questions/50594507/cannot-find-symbol-databindingcomponent-on-android-studio-3-2-canary-16-kotlin-p))

## La arquitectura MVC

* **La vista**: está definida en un .xml
* **El controller**: es el ConversorActivity
* **El modelo de la vista**: es un Conversor que maneja el binding con los controles millas (EditText) y kilómetros (TextView), ambos Strings
* **El modelo de dominio**: es un Conversor que sabe pasar de millas a kilómetros

