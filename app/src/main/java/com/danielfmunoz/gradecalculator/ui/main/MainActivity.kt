package com.danielfmunoz.gradecalculator.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.danielfmunoz.gradecalculator.R
import com.danielfmunoz.gradecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val calculateButton = mainBinding.calculateButton
        calculateButton.setOnClickListener {
            calculateGrade()
        }
    }

    private fun calculateGrade() {
        val labGradeValue = mainBinding.labGrade.text.toString().toDoubleOrNull()
        val proj1GradeValue = mainBinding.proj1Grade.text.toString().toDoubleOrNull()
        val proj2GradeValue = mainBinding.proj2Grade.text.toString().toDoubleOrNull()
        val finalProjGradeValue = mainBinding.finalProjGrade.text.toString().toDoubleOrNull()

        if (labGradeValue == null || proj1GradeValue == null || proj2GradeValue == null || finalProjGradeValue == null) {
            mainBinding.resultText.text = getString(R.string.invalidGrade)
            return
        }

        val labWeight = 0.6
        val proj1Weight = 0.07
        val proj2Weight = 0.08
        val finalProjWeight = 0.25

        val grade =
            labGradeValue * labWeight + proj1GradeValue * proj1Weight + proj2GradeValue * proj2Weight + finalProjGradeValue * finalProjWeight

        mainBinding.resultText.text = "La nota final del curso es: ${String.format("%.2f", grade)}"
    }
}