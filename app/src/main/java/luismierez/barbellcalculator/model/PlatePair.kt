package luismierez.barbellcalculator.model

/**
 * Created by luismierez on 5/7/17.
 */
data class PlatePair(val weight: Double) {

    fun calculateLiftedWeight(): Double {
        return weight * 2
    }
}