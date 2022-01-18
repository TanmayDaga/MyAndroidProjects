package com.example.unitconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.unitconverter.conversions.LENGTH_CODE
import com.example.unitconverter.conversions.convertIt
import com.example.unitconverter.conversions.getArray
import com.example.unitconverter.conversions.getStringFromCode

class ConvertActivity() : AppCompatActivity() {


    var code:Int = 0
    lateinit var spinnerFrom: Spinner
    lateinit var spinnerTo: Spinner
    lateinit var buttonCalculate: Button
    lateinit var imageView: ImageView
    lateinit var fromValue: EditText
    lateinit var endValue: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        initViews()
        val intent: Intent = intent
        code= intent.getIntExtra("CODE", LENGTH_CODE)
        this.supportActionBar?.title = getStringFromCode(code,this)


        val spinnerFromAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this, R.layout.support_simple_spinner_dropdown_item,
            getArray(code)
        )
        spinnerFrom.adapter = spinnerFromAdapter


        val spinnerToAdapter = ArrayAdapter<String>(
            this, R.layout.support_simple_spinner_dropdown_item,
            getArray(code)
        )
        spinnerTo.adapter = spinnerToAdapter


        imageView.setOnClickListener {
            val temp = spinnerTo.selectedItemPosition
            spinnerTo.setSelection(spinnerFrom.selectedItemPosition)
            spinnerFrom.setSelection(temp)

        }


        buttonCalculate.setOnClickListener {
            Calculate()
        }

        spinnerFrom.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Calculate()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinnerTo.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Calculate()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


    }


    fun Calculate(){
        if(!fromValue.text.toString().isEmpty()) {

            endValue.text = convertIt(
                code,
                fromValue.text.toString().toDouble(),
                spinnerFrom.selectedItem.toString(),
                spinnerTo.selectedItem.toString()
            ).toString()
        }

    }

    fun initViews() {
        spinnerFrom = findViewById<Spinner>(R.id.spinnerFrom)
        spinnerTo = findViewById<Spinner>(R.id.spinnerTo)
        imageView = findViewById<ImageView>(R.id.imageView)
        buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        fromValue = findViewById<EditText>(R.id.fromValue)
        endValue = findViewById<TextView>(R.id.endValue)
    }
}


