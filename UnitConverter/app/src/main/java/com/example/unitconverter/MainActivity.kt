package com.example.unitconverter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.unitconverter.conversions.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var customAdapter: UnitAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        customAdapter = UnitAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        customAdapter.setOnItemClickListener(object : UnitAdapter.onItemClickListner {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> startConvertActivity(LENGTH_CODE)
                    1 -> startConvertActivity(AREA_CODE)
                    2 -> startConvertActivity(VOLUME_CODE)
                    3 -> startConvertActivity(SPEED_CODE)
                    4 -> startConvertActivity(WEIGHT_CODE)
                    5 -> startConvertActivity(TEMPERATURE_CODE)
                    6 -> startConvertActivity(POWER_CODE)
                    7 -> startConvertActivity(PRESSURE_CODE)
                }
            }

        })


    }

    private fun startConvertActivity(code: Int) {
        startActivity(Intent(this, ConvertActivity::class.java).putExtra("CODE", code))

    }
}