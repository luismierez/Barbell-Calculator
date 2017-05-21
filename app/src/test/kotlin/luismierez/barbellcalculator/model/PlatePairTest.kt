package luismierez.barbellcalculator.model

import org.junit.Test
import org.junit.Assert.*

@Suppress("IllegalIdentifier")
/**
 * Created by luismierez on 5/9/17.
 */
class PlatePairTest {

    @Test
    fun `test calculate pair weight`() {
        val platePair = PlatePair(45.0)
        assertEquals(45.0 * 2, platePair.calculateLiftedWeight(), 0.0)
    }
}