package com.example.unitconverter

import android.content.Context
import com.example.unitconverter.conversions.*

fun getImage(position:Int):Int{
    
    return when(position){
        0->R.drawable.length
        1->R.drawable.area
        2->R.drawable.volume
        3->R.drawable.speed
        4->R.drawable.weight
        5->R.drawable.thermometer
        6->R.drawable.power
        7->R.drawable.pressure
        else->0
    }
    
    
    
}

fun getString(position:Int,context:Context):String {

    return when (position) {
        0 -> context.resources.getString(R.string.length)
        1 -> context.resources.getString(R.string.area)
        2 -> context.resources.getString(R.string.volume)
        3 -> context.resources.getString(R.string.speed)
        4 -> context.resources.getString(R.string.weight)
        5 -> context.resources.getString(R.string.temperature)
        6 -> context.resources.getString(R.string.power)
        7 -> context.resources.getString(R.string.pressure)
        else -> ""
    }
}

fun getCode(position: Int):Int{
    return when(position){
        0 -> LENGTH_CODE
        1-> AREA_CODE
        2-> VOLUME_CODE
        3-> SPEED_CODE
        4-> WEIGHT_CODE
        5-> TEMPERATURE_CODE
        6-> POWER_CODE
        7-> PRESSURE_CODE
        else->0
    }
}

