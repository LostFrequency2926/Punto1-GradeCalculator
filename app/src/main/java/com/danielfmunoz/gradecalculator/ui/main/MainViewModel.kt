package com.danielfmunoz.gradecalculator.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel :  ViewModel() {


    val calculate: MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }



    fun realizarCalculate(labGradeValue: Double, proj1GradeValue: Double, proj2GradeValue: Double, finalProjGradeValue: Double){
        val labWeight = 0.6
        val proj1Weight = 0.07
        val proj2Weight = 0.08
        val finalProjWeight = 0.25

        calculate.value = labGradeValue * labWeight + proj1GradeValue * proj1Weight + proj2GradeValue * proj2Weight + finalProjGradeValue * finalProjWeight
    }

    fun realizarValidate(labGradeValue: Double, proj1GradeValue: Double, proj2GradeValue: Double, finalProjGradeValue: Double): Boolean {

        return !(labGradeValue < 0 || labGradeValue > 5 || proj1GradeValue < 0 || proj1GradeValue > 5 ||
                proj2GradeValue < 0 || proj2GradeValue > 5 || finalProjGradeValue < 0 || finalProjGradeValue > 5)

    }

    fun realizarValidateNulls(labGradeValue: String, proj1GradeValue: String, proj2GradeValue: String, finalProjGradeValue: String): Boolean {

        return !(labGradeValue.isEmpty() || proj1GradeValue.isEmpty() || proj2GradeValue.isEmpty() || finalProjGradeValue.isEmpty())

    }

}