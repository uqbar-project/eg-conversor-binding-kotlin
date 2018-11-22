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

## Arquitectura MVC

![grafico](/images/Android_Conversor_Binding.png)

* La **vista** se define como un xml
* El **controller** lo define el ConversorActivity
* El **view model** es un ConversorModel, que define los bindings contra la vista y tiene 
 * una propiedad kilómetros read-only de tipo *String* 
 * la propiedad millas de tipo *String*
 * el mensaje convertir que delega al conversor la actualización de los kilómetros y notifica ese cambio a la vista
* El **domain model** es un objeto Conversor, con
 * propiedades kilómetros y millas que son numéricas
 * la responsabilidad de convertir de millas a kilómetros
 
## Configuraciones necesarias para tener binding
 
Tenés que usar gradle 1.5.0 ó superior.

### Configuración de gradle

El archivo [app/build.gradle](/app/build.gradle) -el de la aplicación, no el del proyecto- debe tener estas líneas
 
 ```
 android {
     ...
     dataBinding {
        enabled = true
    }
}
 ```

### Vista

En la [vista](app/src/main/res/layout/activity_conversor.xml) tienen que usar un *view model* definiendo un tag data...
 
```xml
    <data>
        <variable
            name="conversor"
            type="ar.edu.uqbar.conversor_binding.ConversorModel" />
    </data>
```
 
... el data debe estar dentro de un tag layout, que debe comenzar con minúscula (**IMPORTANTE** respetar esto ya que escribir Layout con mayúscula deriva en errores crípticos)
 
```xml
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
```
 
... y luego se puede utilizar el mecanismo de Data Binding. En el caso del TextView que muestra los kilómetros, el binding es _one-way_ (del modelo hacia la vista), se demarca de la siguiente manera:

```xml
        <TextView
            ...
            android:text="@{conversor.kilometros}" />
```

...en el caso del EditText que permite ingresar las millas, queremos que el binding sea _two-way_, para que la vista actualice el modelo y viceversa. Entonces debemos agregar el símbolo = entre el @ y el {:
 
```xml
        <EditText
            ...
            android:text="@={conversor.millas}" />
```


El binding de eventos se da construyendo un closure que define el comportamiento para el botón:
 
 ```xml
         <Button
            ...
            android:onClick="@{() -> conversor.convertir()}"
            android:text="Convertir" />
 ```

### Controller

El [controller](app/src/main/java/ar/edu/uqbar/conversorbindingkotlin/ConversorActivity.kt) define una Activity y genera el binding contra el view model correspondiente:

```kt
class ConversorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)
        val conversor = ConversorModel()
        val binding : ActivityConversorBinding = DataBindingUtil.setContentView(this, R.layout.activity_conversor)
        binding.setConversor(conversor)
    }
}
```

Nótese que no necesitamos implementar onClickListener en la Activity, ya que las notificaciones las manejará el ViewModel, que explicaremos a continuación.

### ViewModel

El modelo de la vista es un [ConversorModel](app/src/main/java/ar/edu/uqbar/conversorbindingkotlin/ConversorActivity.kt) que debe hacer algunas adaptaciones: debe extender de BaseObservable

```kt
class ConversorModel : BaseObservable() {
```

Las propiedades millas y kilómetros son bindeables, esto significa que disparan notificaciones a la vista (activity.xml):

```kt
var millas: String
    @Bindable
    get() = "" + conversor.millas
    @Bindable
    set(millas) = try {
        conversor.millas = BigDecimal(millas)
    } catch (e: java.lang.NumberFormatException) {
    }

val kilometros: String
    @Bindable
    get() = ("" + conversor.kilometros).replace(".", ",")
```

El uso de Strings es necesario ya que el control de Android trabaja con este tipo de valores.
Internamente como vemos delega a un objeto de dominio conversor.

La conversión propiamente dicha debe refrescar la propiedad kilometros de solo lectura, entonces se dispara una notificación en forma manual, de la siguiente manera:

```kt
fun convertir() {
    conversor.convertir()
    notifyPropertyChanged(BR.kilometros)
}
```

### Dominio

El objeto de dominio Conversor (ubicado en el mismo archivo) simplemente define las propiedades kilómetros y millas y una funcionalidad de conversión básica.
