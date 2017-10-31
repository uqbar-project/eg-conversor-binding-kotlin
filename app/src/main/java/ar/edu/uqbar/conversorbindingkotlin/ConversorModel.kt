package ar.edu.uqbar.conversorbindingkotlin

import android.databinding.BaseObservable
import android.databinding.Bindable
import java.math.BigDecimal

/**
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
        get() = "" + conversor.kilometros

}

