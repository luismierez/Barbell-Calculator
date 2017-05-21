package luismierez.barbellcalculator

import org.junit.Assert.*
import org.junit.Test

@Suppress("IllegalIdentifier")
/**
 * Created by luismierez on 5/7/17.
 */
class PlateCalculatorUnitTest {

    @Test
    fun `calculate plates from input weight`() {
        val input = 110.0
        val barWeight = 45.0

        val plateTypes = arrayOf(45.0, 35.0, 25.0, 10.0, 5.0, 2.5)
        var currentPlateType = 0

        var weight = input - barWeight
        weight /= 2

        var result = mutableListOf(0, 0, 0, 0, 0, 0)

        while (currentPlateType < plateTypes.size) {
            while(weight >= plateTypes[currentPlateType]) {
                result[currentPlateType] += 1
                weight -= plateTypes[currentPlateType]
            }
            currentPlateType++
        }

        val expected = listOf(0, 0, 1, 0, 1, 1)

        assertEquals(expected, result)
    }

    @Test
    fun `calculate plates from input weight dumb`() {
        val input = 135.0
        val barWeight = 45.0

        var weight = input - barWeight

        var num45Plates = 0
        var num35Plates = 0
        var num25Plates = 0
        var num10Plates = 0
        var num5Plates = 0
        var numHalf5Plates = 0

        weight /= 2
        while (weight >= 45.0) {
            num45Plates++
            weight -= 45.0
        }

        while (weight >= 35.0) {
            num35Plates++
            weight -= 35.0
        }

        while (weight >= 25.0) {
            num25Plates++
            weight -= 25.0
        }

        while (weight >= 10.0) {
            num10Plates++
            weight -= 10.0
        }

        while (weight >= 5.0) {
            num5Plates++
            weight -= 5.0
        }

        while(weight >= 2.5) {
            numHalf5Plates++
            weight -= 2.5
        }

        // Expected plates
        val expected45Plates = 1
        val expected35Plates = 0
        val expected25Plates = 0
        val expected10Plates = 0
        val expected5Plates = 0
        val expectedHalf5Plates = 0

        assertEquals(expected45Plates, num45Plates)
        assertEquals(expected35Plates, num35Plates)
        assertEquals(expected25Plates, num25Plates)
        assertEquals(expected10Plates, num10Plates)
        assertEquals(expected5Plates, num5Plates)
        assertEquals(expectedHalf5Plates, numHalf5Plates)
    }
}