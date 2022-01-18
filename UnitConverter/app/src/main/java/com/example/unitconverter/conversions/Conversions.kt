package com.example.unitconverter.conversions


fun convertIt(code: Int, toBeConverted: Double, from: String, to: String): Double {
    return when (code) {
        LENGTH_CODE -> convertLength(toBeConverted, from, to)
        AREA_CODE -> convertArea(toBeConverted, from, to)
        VOLUME_CODE -> convertVolume(toBeConverted, from, to)
        SPEED_CODE -> convertSpeed(toBeConverted, from, to)
        WEIGHT_CODE -> convertWeight(toBeConverted, from, to)
        TEMPERATURE_CODE -> convertTemperature(toBeConverted, from, to)
        POWER_CODE -> convertPower(toBeConverted, from, to)
        PRESSURE_CODE -> convertPressure(toBeConverted, from, to)
        else -> return 0.0
    }
}

private fun convertLength(toBeConverted: Double, from: String, to: String): Double {


    fun getFromMetre(toBeConverted: Double, to: String): Double {
        return when (to) {
            METRE -> toBeConverted
            KILOMETRE -> toBeConverted *0.001
            DECIMETRE -> toBeConverted * 10
            CENTIMETRE -> toBeConverted * 100
            MILLIMETRE -> toBeConverted * 1000
            MICROMETRE -> toBeConverted * 1E+6
            NANOMETRE -> toBeConverted * 1E+9
            PICOMETRE -> toBeConverted * 1E+12
            LIGHT_YEAR -> toBeConverted / 9.461E+15
            PARSEC -> toBeConverted / 3.08E+16
            LUNAR_DISTANCE -> toBeConverted / 3.844E+8
            ASTRONOMICAL_UNIT -> toBeConverted / 1.496E+11
            INCH -> toBeConverted * 39.37
            MILE -> toBeConverted / 1609
            FOOT -> toBeConverted * 3.281
            YARD -> toBeConverted * 1.094
            NAUTICAL_MILE -> toBeConverted / 1852
            else -> toBeConverted
        }
    }

    return when (from) {
        METRE -> return getFromMetre(toBeConverted, to)
        KILOMETRE -> getFromMetre(toBeConverted, to) / 0.001
        DECIMETRE -> getFromMetre(toBeConverted, to) / 10
        CENTIMETRE -> getFromMetre(toBeConverted, to) / 100
        MILLIMETRE -> getFromMetre(toBeConverted, to) / 1000
        MICROMETRE -> getFromMetre(toBeConverted, to) / 1E+6
        NANOMETRE -> getFromMetre(toBeConverted, to) / 1E+9
        PICOMETRE -> getFromMetre(toBeConverted, to) / 1E+12
        LIGHT_YEAR -> getFromMetre(toBeConverted, to) * 9.461E+15
        PARSEC -> getFromMetre(toBeConverted, to) * 3.08E+16
        LUNAR_DISTANCE -> getFromMetre(toBeConverted, to) * 3.884E+8
        ASTRONOMICAL_UNIT -> getFromMetre(toBeConverted, to) * 1.496E+11
        INCH -> getFromMetre(toBeConverted, to) / 39.37
        MILE -> getFromMetre(toBeConverted, to) * 1609
        FOOT -> getFromMetre(toBeConverted, to) / 3.281
        YARD -> getFromMetre(toBeConverted, to) / 1.094
        NAUTICAL_MILE -> getFromMetre(toBeConverted, to) * 1852
        else -> toBeConverted
    }

}

private fun convertArea(toBeConverted: Double, from: String, to: String): Double {
    fun getFromSquareMetre(toBeConverted: Double, to: String): Double {
        return when (to) {
            SQUARE_KILOMETER -> toBeConverted * 0.000001
            HECTARE -> toBeConverted * 0.0001
            Are -> 0.01 * toBeConverted
            SQUARE_METRE -> toBeConverted
            SQUARE_DECIMETRE -> 100 * toBeConverted
            SQUARE_CENTIMETER -> 10000 * toBeConverted
            SQUARE_MILLIMETER -> toBeConverted * 1e+6
            ACRE -> toBeConverted * 0.0002471
            SQUARE_MILE -> toBeConverted * 3.861e-7
            SQUARE_YARD -> toBeConverted * 1.19599
            SQUARE_FOOT -> toBeConverted * 10.7639104
            SQUARE_INCH -> toBeConverted * 1550.0031
            SQUARE_ROD -> toBeConverted * 0.0395369
            else -> toBeConverted

        }
    }

    return when (from) {
        SQUARE_KILOMETER -> getFromSquareMetre(toBeConverted, to) / 0.000001
        HECTARE -> getFromSquareMetre(toBeConverted, to) / 0.0001
        Are -> getFromSquareMetre(toBeConverted, to) / 0.01
        SQUARE_METRE -> getFromSquareMetre(toBeConverted, to)
        SQUARE_DECIMETRE -> getFromSquareMetre(toBeConverted, to) / 100
        SQUARE_CENTIMETER -> getFromSquareMetre(toBeConverted, to) / 10000
        SQUARE_MILLIMETER -> getFromSquareMetre(toBeConverted, to) / 1E+6
        ACRE -> getFromSquareMetre(toBeConverted, to) / 0.00002471
        SQUARE_MILE -> getFromSquareMetre(toBeConverted, to) / 3.861E-7
        SQUARE_YARD -> getFromSquareMetre(toBeConverted, to) / 1.19599
        SQUARE_FOOT -> getFromSquareMetre(toBeConverted, to) / 10.7639104
        SQUARE_INCH -> getFromSquareMetre(toBeConverted, to) / 1550.0031
        SQUARE_ROD -> getFromSquareMetre(toBeConverted, to) / 0.0395369
        else -> toBeConverted
    }


}

private fun convertVolume(toBeConverted: Double, from: String, to: String): Double {

    fun getFromCubicMetre(toBeConverted: Double, to: String): Double {
        return when (to) {
            HECTOLITRE -> toBeConverted * 10
            CUBIC_METRE -> toBeConverted
            CUBIC_DECIMETRE -> 1000 * toBeConverted
            CUBIC_CENTIMETRE -> 1000000 * toBeConverted
            CUBIC_MILLIMETRE -> 1000000000 * toBeConverted
            LITRE -> 1000 * toBeConverted
            DECILITRE -> 10000 * toBeConverted
            CENTILITRE -> 100000 * toBeConverted
            MILLILITRE -> 1000000 * toBeConverted
            CUBIC_FOOT -> 35.3147248 * toBeConverted
            CUBIC_INCH -> 61023.844 * toBeConverted
            CUBIC_YARD -> 1.3079 * toBeConverted
            ACRE_FOOT -> 0.0008107 * toBeConverted
            else -> toBeConverted
        }
    }

    return when (from) {
        HECTOLITRE -> getFromCubicMetre(toBeConverted, to) / 10
        CUBIC_METRE -> getFromCubicMetre(toBeConverted, to)
        CUBIC_DECIMETRE -> getFromCubicMetre(toBeConverted, to) / 1000
        CUBIC_MILLIMETRE -> getFromCubicMetre(toBeConverted, to) / 1000000000
        LITRE -> getFromCubicMetre(toBeConverted, to) / 1000
        DECILITRE -> getFromCubicMetre(toBeConverted, to) / 10000
        CENTILITRE -> getFromCubicMetre(toBeConverted, to) / 100000
        MILLILITRE -> getFromCubicMetre(toBeConverted, to) / 1000000
        CUBIC_FOOT -> getFromCubicMetre(toBeConverted, to) / 35.3147248
        CUBIC_INCH -> getFromCubicMetre(toBeConverted, to) / 61023.844
        CUBIC_YARD -> getFromCubicMetre(toBeConverted, to) / 1.3079
        ACRE_FOOT -> getFromCubicMetre(toBeConverted, to) / 0.0008107
        else -> toBeConverted
    }
}

private fun convertSpeed(toBeConverted: Double, from: String, to: String): Double {
    fun getFromMetreSecond(toBeConverted: Double,to: String):Double{
        return when (to){
            METRE_SECOND->toBeConverted
            KILOMETRE_SECOND->0.001*toBeConverted
            KILOMETRE_HOUR->3.6*toBeConverted
            SPEED_OF_LIGHT->3.3356e-9*toBeConverted
            MILE_HOUR->2.236936*toBeConverted
            MACH->0.0029386*toBeConverted
            INCH_SECOND->39.370079*toBeConverted
            else->toBeConverted
        }
    }
    return when(from){
        METRE_SECOND->getFromMetreSecond(toBeConverted, to)
        KILOMETRE_SECOND->getFromMetreSecond(toBeConverted, to)/0.001
        KILOMETRE_HOUR->getFromMetreSecond(toBeConverted, to)/3.6
        SPEED_OF_LIGHT->getFromMetreSecond(toBeConverted, to)/3.3356-9
        MILE_HOUR->getFromMetreSecond(toBeConverted, to)/2.236936
        MACH->getFromMetreSecond(toBeConverted, to)/0.0028386
        INCH_SECOND->getFromMetreSecond(toBeConverted, to)/39.370079
        else->toBeConverted

    }
}

private fun convertWeight(toBeConverted: Double, from: String, to: String): Double {
    fun getFromKg(toBeConverted: Double,to: String):Double{
        return when(to){
            KILOGRAM->toBeConverted
            GRAM->1000*toBeConverted
            MILLIGRAM->1000000*toBeConverted
            MICRO_GRAM->1000000000*toBeConverted
            TONNE->0.001*toBeConverted
            QUINTAL->0.01*toBeConverted
            CARAT->5000*toBeConverted
            POUND->2.2046226*toBeConverted
            OUNCE->35.2739619*toBeConverted
            GRAIN->15432.3583529*toBeConverted
            else->toBeConverted

        }
    }

    return when(from){
        KILOGRAM->getFromKg(toBeConverted, to)
        GRAM->getFromKg(toBeConverted, to)/1000
        MILLIGRAM->getFromKg(toBeConverted, to)/10000000
        MICRO_GRAM->getFromKg(toBeConverted, to)/1000000000
        TONNE->getFromKg(toBeConverted, to)/0.001
        QUINTAL->getFromKg(toBeConverted, to)/0.01
        CARAT->getFromKg(toBeConverted, to)/5000
        POUND->getFromKg(toBeConverted, to)/2.2046226
        OUNCE->getFromKg(toBeConverted, to)/35.2739619
        GRAIN->getFromKg(toBeConverted, to)/15432.3583529
        else->toBeConverted
    }
}

private fun convertTemperature(toBeConverted: Double, from: String, to: String): Double {
    fun getFromCelsius(toBeConverted: Double,to: String):Double{
        return when(to){
            CELSIUS->1*toBeConverted
            FAHRENHEIT->(toBeConverted*(9/5))+32
            REAUMUR -> 0.8*toBeConverted
            RANKINE->(toBeConverted*(9/5))+491.67
            KELVIN->toBeConverted+273.15
            else -> toBeConverted
        }

    }

    return when(from){
        CELSIUS->getFromCelsius(toBeConverted,to)
        FAHRENHEIT->(getFromCelsius(toBeConverted, to)-32)*9/5
        REAUMUR->getFromCelsius(toBeConverted, to)/0.8
        RANKINE->(getFromCelsius(toBeConverted,to)-491.67)*5/9
        KELVIN->getFromCelsius(toBeConverted, to)-273.15
        else -> toBeConverted
    }


}

private fun convertPower(toBeConverted: Double, from: String, to: String): Double {
    fun getFromWatt(toBeConverted: Double,to: String):Double{
        return when(to){
            WATT->toBeConverted
            KILOWATT->toBeConverted*0.001
            JOULE_SECOND->toBeConverted
            IMPERIAL_HORSE_POWER->0.0013410221*toBeConverted
            METRIC_POWER->toBeConverted*0.0013596216
            KILOGRAM_METRE_SECOND->toBeConverted*0.101976213
            KILOCALORIE_SECOND->0.000239*toBeConverted
            BRITISH_THERMAL_UNIT_SECOND->0.0009478171*toBeConverted
            FOOT_POUND_SECOND ->toBeConverted*0.7375621489
            NEWTON_METRE_SECOND->toBeConverted
            else->toBeConverted
        }
    }

    return when(from){
        WATT->getFromWatt(toBeConverted, to)
        KILOWATT->getFromWatt(toBeConverted, to)/0.001
        JOULE_SECOND->getFromWatt(toBeConverted, to)
        IMPERIAL_HORSE_POWER->getFromWatt(toBeConverted, to)/0.0013410221
        METRIC_POWER->getFromWatt(toBeConverted, to)/0.0013596216
        KILOGRAM_METRE_SECOND->getFromWatt(toBeConverted, to)/0.101976213
        KILOCALORIE_SECOND->getFromWatt(toBeConverted, to)/0.000239
        BRITISH_THERMAL_UNIT_SECOND->getFromWatt(toBeConverted, to)/0.000239
        FOOT_POUND_SECOND->getFromWatt(toBeConverted, to)/0.7375621489
        NEWTON_METRE_SECOND->getFromWatt(toBeConverted, to)
        else->toBeConverted

    }
}

private fun convertPressure(toBeConverted: Double, from: String, to: String): Double {
    fun getFromAtmosphere(toBeConverted: Double,to: String):Double{
        return when(to){
            STANDARD_ATMOSPHERIC_PRESSURE->toBeConverted
            HECTOPASCAL->toBeConverted*1013.25027
            KILOPASCAL->toBeConverted*101.325027
            MEGAPASCAL->toBeConverted*0.101325027
            MILLIMETRE_OF_MERCURY->toBeConverted*760.0002229
            INCH_OF_MERCURY->29.921280*toBeConverted
            BAR->1.01325027*toBeConverted
            MILLIBAR->1013.25027*toBeConverted
            POUNDS_SQUARE_INCH->14.6959297708*toBeConverted
            POUNDS_SQUARE_FOOT->2116.2178544*toBeConverted
            KILOGRAM_FORCE_SQUARE_METRE->10332.2772901*toBeConverted
            KILOGRAM_FORCE_SQUARE_CENTIMETRE->1.033211304*toBeConverted
            MILLIMETRE_OF_WATER->10332.271622973*toBeConverted
            else->toBeConverted

        }
    }

    return when(from){
        STANDARD_ATMOSPHERIC_PRESSURE->getFromAtmosphere(toBeConverted, to)
        HECTOPASCAL->getFromAtmosphere(toBeConverted, to)/1013.25027
        KILOPASCAL->getFromAtmosphere(toBeConverted, to)/101.325027
        MEGAPASCAL->getFromAtmosphere(toBeConverted, to)/0.101325027
        MILLIMETRE_OF_MERCURY->getFromAtmosphere(toBeConverted, to)/760.0002229
        INCH_OF_MERCURY->getFromAtmosphere(toBeConverted, to)/29.921280
        BAR->getFromAtmosphere(toBeConverted, to)/1.01325027
        MILLIBAR->getFromAtmosphere(toBeConverted, to)/1013.25027
        POUNDS_SQUARE_INCH->getFromAtmosphere(toBeConverted, to)/14.6959297708
        POUNDS_SQUARE_FOOT->getFromAtmosphere(toBeConverted, to)/2116.2178544
        KILOGRAM_FORCE_SQUARE_METRE->getFromAtmosphere(toBeConverted, to)/10332.2772901
        KILOGRAM_FORCE_SQUARE_CENTIMETRE->getFromAtmosphere(toBeConverted, to)/1.033211304
        MILLIMETRE_OF_WATER->getFromAtmosphere(toBeConverted, to)/10332.271622973
        else->toBeConverted

    }
}
