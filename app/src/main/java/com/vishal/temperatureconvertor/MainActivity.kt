package com.vishal.temperatureconvertor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.vishal.temperatureconvertor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Toast.makeText(this, "Designed and Developed by Vanshika Madnawat", Toast.LENGTH_LONG).show()

        val fromSpinner: Spinner = findViewById(R.id.from_spinner)
        val toSpinner: Spinner = findViewById(R.id.to_spinner)
        var fromUnit: String
        var toUnit: String
        var result: Float

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.temperature_unit,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner
            fromSpinner.adapter = adapter
            fromSpinner.onItemSelectedListener = this

            toSpinner.adapter = adapter
            toSpinner.onItemSelectedListener = this
        }

        binding.convertButton.setOnClickListener {
            fromUnit = setSpinnerItem(fromSpinner)
            toUnit = setSpinnerItem(toSpinner)
            if (fromUnit == "Celsius" && toUnit == "Fahrenheit") {
                result = (getTemperature() * 9 / 5) + 32
                setTemperature(result)
            } else if (fromUnit == "Fahrenheit" && toUnit == "Celsius") {
                result = (getTemperature() - 32) * 5 / 9
                setTemperature(result)
            } else if (fromUnit == "Fahrenheit" && toUnit == "Fahrenheit") {
                result = (getTemperature())
                setTemperature(result)
            } else if (fromUnit == "Celsius" && toUnit == "Celsius") {
                result = (getTemperature())
                setTemperature(result)
            }
            else Toast.makeText(this, "Invalid format", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTemperature(result: Float) {
        binding.resultText.text = result.toString()
    }

    private fun getTemperature(): Float {
        return binding.tempEditText.text.toString().toFloat()
    }

    private fun setSpinnerItem(spinner: Spinner): String {
        return spinner.selectedItem.toString()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}