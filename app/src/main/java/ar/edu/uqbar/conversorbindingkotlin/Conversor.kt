package ar.edu.uqbar.conversorbindingkotlin

import java.math.BigDecimal

/**
 * Created by fernando on 10/26/17.
 */
class Conversor {
    val FACTOR_CONVERSION = 1.609344
    var millas = BigDecimal(0)
    var kilometros = BigDecimal(0)

    fun convertir() {
        kilometros = millas.multiply(BigDecimal(FACTOR_CONVERSION))
    }
}
