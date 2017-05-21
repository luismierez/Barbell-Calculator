package luismierez.barbellcalculator

import io.reactivex.Observable
import luismierez.barbellcalculator.model.PlatePair
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.mockito.Mockito.*

/**
 * Created by luismierez on 5/10/17.
 */
class MainPresenterTest {

    val mockView: MainContract.View = mock(MainContract.View::class.java)
    lateinit var presenter: MainPresenter

    @Before
    fun setup() {
        presenter = MainPresenter(mockView)
    }

    @Test
    fun initializesView() {
        verify(mockView).displayTotalLiftWeight("45.0")
    }

    @Test
    fun calculateLiftWeightTest() {
        val expectedTotalLiftWeight = "135.0"

        presenter.addPlatePair(PlatePair(45.0))
        presenter.calculateLiftWeight()
        verify(mockView, atLeastOnce()).displayTotalLiftWeight(expectedTotalLiftWeight)

    }

    @Test
    fun calculatePlatesFromEditText() {
        presenter.registerWeight(Observable.just("135.0"))
        verify(mockView, atLeastOnce()).displayPlatesNeeded(listOf(1, 0, 0, 0, 0, 0))
    }

    @Test
    fun calculatePlates() {
        val plates = presenter.calculatePlatePairs(135.0)
        assertArrayEquals(listOf(1, 0, 0, 0, 0, 0).toIntArray(), plates.toIntArray())
    }

    @Test
    fun removePlateAndCalculateWeightTest() {
        val expectedTotalLiftWeight = "45.0"

        presenter.addPlatePair(PlatePair(45.0))
        presenter.removePlatePair(PlatePair(45.0))

        verify(mockView, atLeastOnce()).displayTotalLiftWeight(expectedTotalLiftWeight)
    }
}