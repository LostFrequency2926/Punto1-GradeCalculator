package com.danielfmunoz.gradecalculator.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.danielfmunoz.gradecalculator.R
import com.danielfmunoz.gradecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var labGradeValue: Double = 0.0
    private var proj1GradeValue: Double = 0.0
    private var proj2GradeValue: Double = 0.0
    private var finalProjGradeValue: Double = 0.0

    private lateinit var mainBinding: ActivityMainBinding
    private  lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val calculateObserver = Observer<Double> {calculate ->
            mainBinding.resultText.text = "${getString(R.string.finalGrade)} ${String.format("%.2f", calculate.toDouble())}"

        }
        mainViewModel.calculate.observe(this, calculateObserver)

        setContentView(mainBinding.root)

        val calculateButton = mainBinding.calculateButton

        calculateButton.setOnClickListener {
            calculateGrade()
        }
    }

    private fun calculateGrade() {

        if (mainViewModel.realizarValidateNulls(mainBinding.labGrade.text.toString(), mainBinding.proj1Grade.text.toString(),mainBinding.proj2Grade.text.toString(), mainBinding.finalProjGrade.text.toString() )) {
            leerDatos()
            if(mainViewModel.realizarValidate(labGradeValue,proj1GradeValue,proj2GradeValue,finalProjGradeValue)){
                mainViewModel.realizarCalculate(labGradeValue, proj1GradeValue, proj2GradeValue,finalProjGradeValue)
            }else{
                Toast.makeText(this, getString(R.string.maximunGrade), Toast.LENGTH_SHORT).show()
                mainBinding.resultText.text = ""
            }

        } else {
                Toast.makeText(this, getString(R.string.fillAllGrades), Toast.LENGTH_SHORT).show()
            mainBinding.resultText.text = ""
            }

    }

    private fun leerDatos() {
        labGradeValue = mainBinding.labGrade.text.toString().toDouble()
        proj1GradeValue = mainBinding.proj1Grade.text.toString().toDouble()
        proj2GradeValue = mainBinding.proj2Grade.text.toString().toDouble()
        finalProjGradeValue = mainBinding.finalProjGrade.text.toString().toDouble()
    }

}