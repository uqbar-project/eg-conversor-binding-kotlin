package ar.edu.uqbar.conversorbindingkotlin

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ar.edu.uqbar.conversorbindingkotlin.databinding.ActivityConversorBinding


class ConversorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)
        val conversor = ConversorModel()
        val binding : ActivityConversorBinding = DataBindingUtil.setContentView(this, R.layout.activity_conversor)
        binding.setConversor(conversor)
    }
}
