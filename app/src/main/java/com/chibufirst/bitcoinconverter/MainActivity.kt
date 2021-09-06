package com.chibufirst.bitcoinconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chibufirst.bitcoinconverter.databinding.ActivityMainBinding
import java.math.RoundingMode
import kotlin.math.round

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConvert.setOnClickListener {
            convertToBitcoin()
        }
    }

    private fun convertToBitcoin() {
        val number = binding.textInputNumber.editText?.text.toString().toDoubleOrNull()
        if (number == null || number == 0.0) {
            displayResult(0.0)
            return
        }

        val bitcoinValue = 0.0000000468451008
        var result = number * bitcoinValue
        result = if (binding.switchRound.isChecked) {
            round(result)
        } else {
           result.toBigDecimal().setScale(2, RoundingMode.UP).toDouble()
        }

        displayResult(result)
    }

    private fun displayResult(result: Double) {
        val value = "$result"
        binding.textResult.text = getString(R.string.result, value)
    }
}