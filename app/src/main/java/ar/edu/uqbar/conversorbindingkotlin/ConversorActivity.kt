package ar.edu.uqbar.conversorbindingkotlin

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import ar.edu.uqbar.conversorbindingkotlin.databinding.ActivityConversorBinding
import android.databinding.BaseObservable
import android.databinding.Bindable
import java.math.BigDecimal

/**
 * MODELO DE DOMINIO
 *
 * Un conversor que sabe convertir de millas a kilómetros
 *
 * Created by fernando on 10/26/17.
 */
class Conversor {
    val FACTOR_CONVERSION = 1.609344
    var millas = BigDecimal(0)
    var kilometros = BigDecimal(0)

    fun convertir() {
        kilometros = millas.multiply(BigDecimal(FACTOR_CONVERSION)).setScale(3, BigDecimal.ROUND_HALF_UP)
    }
}

/**
 * MODELO DE VISTA
 *
 * Un conversor que sabe manejar conversiones entre controles millas a kilómetros
 * Esto incluye manejo de Strings
 *
 * Created by fernando on 10/26/17.
 */
class ConversorModel : BaseObservable() {
    private val conversor = Conversor()

    fun convertir() {
        conversor.convertir()
        notifyPropertyChanged(BR.kilometros)
    }

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

}


/**
 * CONTROLLER
 *
 * Define el binding entre vista (xml) y modelo de vista
 *
 * Created by fernando on 10/26/17.
 */

class ConversorActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)
        val conversor = ConversorModel()
        val binding : ActivityConversorBinding = DataBindingUtil.setContentView(this, R.layout.activity_conversor)
        binding.setConversor(conversor)
    }
}
