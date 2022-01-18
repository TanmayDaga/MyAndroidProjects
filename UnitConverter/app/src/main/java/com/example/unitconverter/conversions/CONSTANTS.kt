package com.example.unitconverter.conversions

import android.content.Context
import com.example.unitconverter.R
import com.example.unitconverter.getString


const val LENGTH_CODE= 23
const val AREA_CODE = 45
const val VOLUME_CODE = 32
const val SPEED_CODE = 78
const val WEIGHT_CODE = 90
const val TEMPERATURE_CODE = 34
const val POWER_CODE = 67
const val PRESSURE_CODE = 12

//    Length

const val METRE = "Metre"
const val KILOMETRE = "Kilometer"
const val DECIMETRE = "Decimeter"
const val CENTIMETRE = "Centimeter"
const val MILLIMETRE = "Millimeter"
const val MICROMETRE = "Micro Meter"
const val NANOMETRE = "Nano Meter"
const val PICOMETRE = "Pico Meter"
const val LIGHT_YEAR = "Light Year"
const val PARSEC = "Parsec"
const val LUNAR_DISTANCE = "Lunar Distance"
const val ASTRONOMICAL_UNIT = "Astronomical Unit"

const val INCH = "Inch"
const val MILE = "Mile"
const val FOOT = "Foot"
const val YARD = "Yard"
const val NAUTICAL_MILE = "Nautical Mile"


//    Area
const val SQUARE_KILOMETER = "Square Kilometer"
const val HECTARE = "Hectare"
const val Are = "Are"
const val SQUARE_METRE = "Square Metre"
const val SQUARE_DECIMETRE = "Square Decimeter"
const val SQUARE_CENTIMETER = "Square Centimeter"
const val SQUARE_MILLIMETER = "Square Millimeter"

const val ACRE = "Acre"
const val SQUARE_MILE = "Square Mile"
const val SQUARE_YARD = "Square Yard"
const val SQUARE_FOOT = "Square Foot"
const val SQUARE_INCH = "Square Inch"
const val SQUARE_ROD = "Square Rod"


//Volume
const val HECTOLITRE = "Hectolitre"
const val CUBIC_METRE = "Cubic Metre"
const val CUBIC_DECIMETRE = "Cubic Decimetre"
const val CUBIC_CENTIMETRE = "Cubic Centimetre"
const val CUBIC_MILLIMETRE = "Cubic Millimetre"
const val LITRE = "Litre"
const val DECILITRE = "Decilitre"
const val CENTILITRE = "Centilitre"
const val MILLILITRE = "Millilitre"
const val CUBIC_FOOT = "Cubic Foot"
const val CUBIC_INCH = "Cubic Inch"
const val CUBIC_YARD = "Cubic Yard"
const val ACRE_FOOT = "Acre Foot"


//Speed
const val METRE_SECOND = "Metre/Second"
const val KILOMETRE_SECOND = "Kilometre/Second"
const val KILOMETRE_HOUR = "Kilometre/Hour"
const val SPEED_OF_LIGHT = "Speed Of Light"
const val MILE_HOUR = "Mile/Hour"
const val MACH = "Mach"
const val INCH_SECOND = "Inch/Second"


//Weight
const val KILOGRAM = "Kilogram"
const val GRAM = "Gram"
const val MILLIGRAM = "MilliGram"
const val MICRO_GRAM = "Micro gram"
const val TONNE = "Tonne"
const val QUINTAL = "Quintal"
const val CARAT = "Carat"

const val POUND = "Pound"
const val OUNCE = "Ounce"
const val GRAIN = "Grain"


//Temperature
const val CELSIUS = "Degree Celsius"
const val FAHRENHEIT = "Degree Fahrenheit"
const val REAUMUR = "Degree Reaumur"
const val RANKINE = "Degree Rankine"
const val KELVIN = "Kelvin"


//Power
const val KILOWATT = "Kilowatt"
const val WATT = "Watt"
const val JOULE_SECOND = "Joule /Second"
const val IMPERIAL_HORSE_POWER = "Horse Power"
const val METRIC_POWER = "Metric Power"
const val KILOGRAM_METRE_SECOND = "Kilogram-metre/second"
const val KILOCALORIE_SECOND = "Kilocalorie/second"
const val BRITISH_THERMAL_UNIT_SECOND = "British Thermal Unit/second"
const val FOOT_POUND_SECOND = "Foot Pound"
const val NEWTON_METRE_SECOND = "Newton-metre/second"

//PRESSURE
const val HECTOPASCAL = "Hectopascal"
const val KILOPASCAL = "Kilopascal"
const val MEGAPASCAL = "Megapascal"
const val MILLIMETRE_OF_MERCURY = "Millimeter of mercury"
const val INCH_OF_MERCURY = "Inch of mercury"
const val BAR = "Bar"
const val MILLIBAR = "MilliBar"
const val POUNDS_SQUARE_INCH = "Pounds/square inch"
const val POUNDS_SQUARE_FOOT = "Pounds/square foot"
const val KILOGRAM_FORCE_SQUARE_METRE = "Kilogram-force/square metre"
const val KILOGRAM_FORCE_SQUARE_CENTIMETRE = "Kilogram-force/square centimetre"
const val MILLIMETRE_OF_WATER = "Millimetre of water"
const val STANDARD_ATMOSPHERIC_PRESSURE= "Standard Atmosphere"



fun getArray(code:Int):Array<String>{
    return when(code){
        LENGTH_CODE-> LengthArray
        AREA_CODE-> AreaArray
        VOLUME_CODE-> volumeArray
        SPEED_CODE-> speedArray
        WEIGHT_CODE-> weightArray
        TEMPERATURE_CODE-> temperatureArray
        POWER_CODE-> powerArray
        PRESSURE_CODE-> pressureArray
        else-> LengthArray
    }
}

fun getStringFromCode(code: Int,context: Context):String{
    return when(code){
        LENGTH_CODE-> context.getString(R.string.length)
        AREA_CODE->context.getString(R.string.area)
        VOLUME_CODE->context.getString(R.string.volume)
        SPEED_CODE->context.getString(R.string.speed)
        WEIGHT_CODE->context.getString(R.string.weight)
        TEMPERATURE_CODE->context.getString(R.string.temperature)
        POWER_CODE->context.getString(R.string.power)
        PRESSURE_CODE->context.getString(R.string.pressure)
        else->""
    }
}

val LengthArray = arrayOf(
    METRE, KILOMETRE, DECIMETRE, CENTIMETRE, MILLIMETRE, MICROMETRE,
    NANOMETRE, MICROMETRE, PICOMETRE, LIGHT_YEAR, PARSEC, LUNAR_DISTANCE, ASTRONOMICAL_UNIT,
    INCH, MILE, FOOT, YARD, NAUTICAL_MILE
)

val AreaArray = arrayOf(
    SQUARE_KILOMETER, HECTARE, Are, SQUARE_METRE, SQUARE_DECIMETRE, SQUARE_CENTIMETER,
    SQUARE_MILE, SQUARE_MILLIMETER, ACRE, SQUARE_ROD, SQUARE_YARD, SQUARE_FOOT, SQUARE_INCH
)


val volumeArray = arrayOf(
    HECTOLITRE, CUBIC_METRE, CUBIC_DECIMETRE, CUBIC_CENTIMETRE, CUBIC_MILLIMETRE,
    LITRE, DECILITRE, CENTILITRE, MILLILITRE, CUBIC_FOOT, CUBIC_INCH, CUBIC_YARD, ACRE_FOOT
)


val speedArray = arrayOf(
    METRE_SECOND, KILOMETRE_SECOND, KILOMETRE_HOUR, SPEED_OF_LIGHT, MILE_HOUR, MACH, INCH_SECOND
)

val weightArray =
    arrayOf(KILOGRAM, MILLIGRAM, GRAM, MICRO_GRAM, TONNE, QUINTAL, CARAT, POUND, OUNCE, GRAIN)


val temperatureArray = arrayOf(CELSIUS, FAHRENHEIT, REAUMUR, RANKINE, KELVIN)

val powerArray = arrayOf(
    KILOWATT, WATT, JOULE_SECOND, IMPERIAL_HORSE_POWER, METRIC_POWER,
    KILOGRAM_METRE_SECOND, KILOCALORIE_SECOND, BRITISH_THERMAL_UNIT_SECOND, FOOT_POUND_SECOND,
    NEWTON_METRE_SECOND
)


val pressureArray = arrayOf(
    HECTOPASCAL, KILOPASCAL, MEGAPASCAL, MILLIMETRE_OF_MERCURY, INCH_OF_MERCURY, BAR,
    MILLIBAR, POUNDS_SQUARE_INCH, POUNDS_SQUARE_FOOT, KILOGRAM_FORCE_SQUARE_METRE,
    KILOGRAM_FORCE_SQUARE_CENTIMETRE, MILLIMETRE_OF_WATER, STANDARD_ATMOSPHERIC_PRESSURE
)