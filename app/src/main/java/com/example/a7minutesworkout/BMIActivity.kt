package com.example.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkout.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"    //Metric unit view
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"    //Us unit view
    }

    private var binding :  ActivityBmiBinding? = null
    private var currentVisibleView: String =
        METRIC_UNITS_VIEW  // A varialble to hold a value to make a selected view visible


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBmiActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId:Int ->
            if(checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUsUnitsView()
            }

        }


        binding?.btnCalculateUnits?.setOnClickListener {

            calculateUnits()
        }
    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE //Metric height units visible
        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE //Metric Weight units visible
        binding?.tilUsMetricUnitWeight?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.GONE
        binding?.tilMetricUsUnitHeightInch?.visibility = View.GONE

        binding?.etMetricUnitHeight?.text!!.clear() //Height value is cleared if it is added
        binding?.etMetricUnitWeight?.text!!.clear() //Weight value is cleared if it is added

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

    }
    private fun makeVisibleUsUnitsView(){
        currentVisibleView = US_UNITS_VIEW
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE //Metric height units invisible
        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE //Metric Weight units invisible
        binding?.tilUsMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilMetricUsUnitHeightFeet?.visibility = View.VISIBLE //Metric height units visible
        binding?.tilMetricUsUnitHeightInch?.visibility = View.VISIBLE

        binding?.etUsMetricUnitWeight?.text!!.clear()       //Weight value is cleared if it is added
        binding?.etUsMetricUnitHeightFeet?.text!!.clear()   //Height Feet value is cleard
        binding?.etUsMetricUnitHeightInch?.text!!.clear()   //Height Inch value is cleard

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE

    }


    private fun displayBMIResult(bmi: Float){
        val bmiLable : String
        val bmiDescription:String

        if(bmi.compareTo(15f) <= 0){
            bmiLable= "Very severly undrweight"
            bmiDescription= "Oops! you really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLable= "Severly UnderWeight"
            bmiDescription= "Oops! you really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
            bmiLable= "UnderWeight"
            bmiDescription= "Oops! you really need to take better care of yourself! Eat more!"
        }else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0){
            bmiLable= "Normal"
            bmiDescription= "Congratulations! You are in a good shape. Keep it up!"
        }else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0){
            bmiLable= "OverWeight"
            bmiDescription= "Oops! you really need to take better care of yourself! Workout"
        }else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0){
            bmiLable= "Obese Class | (Moderately obese)"
            bmiDescription= "Oops! you really need to take better care of yourself! Workout!"
        }else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0){
            bmiLable= "Obese Class |(Fat)"
            bmiDescription= "OMG! You are in a very dangerouse condition! Act Now!"
        }else {
            bmiLable = "Obese Class |(Very Fat)"
            bmiDescription = "OMG! You are in a very dangerouse condition! Act Now!"
        }
        val bmiValue = BigDecimal(bmi.toDouble())
            .setScale(2, RoundingMode.HALF_EVEN).toString()

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text =bmiLable
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private  fun validateMetricUnits(): Boolean {
        var isValid = true
        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()) {
            isValid = false
        } else if (binding?.etMetricUnitHeight?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }

    private fun calculateUnits(){
        if(currentVisibleView == METRIC_UNITS_VIEW){
            if(validateMetricUnits()){
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi : Float = weightValue / (heightValue*heightValue)

                displayBMIResult(bmi)

            }else{
                Toast.makeText(this@BMIActivity,
                    "Please enter valid values",
                    Toast.LENGTH_SHORT).show()
            }

        }else{
            if(validateUsUnits()){
                val usUnitHeightValueFeet: String =
                    binding?.etUsMetricUnitHeightFeet?.text.toString()
                val usUnitHeightValueInch: String =
                    binding?.etUsMetricUnitHeightInch?.text.toString()
                val usUnitWeightValue: Float =
                    binding?.etUsMetricUnitWeight?.text.toString().toFloat()

             //Height has been converted in inches here
                val heightValue =
                    usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat()*12

                val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))

                displayBMIResult(bmi)
            }else{
                Toast.makeText(this@BMIActivity,
                    "Please enter valid values",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }


    private  fun validateUsUnits(): Boolean {
        var isValid = true
        when {
            binding?.etUsMetricUnitWeight?.text.toString().isEmpty() -> {
                isValid = false
            }

            binding?.etUsMetricUnitHeightFeet?.text.toString().isEmpty() -> {
                isValid = false
            }

            binding?.etUsMetricUnitHeightInch?.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
    }
}